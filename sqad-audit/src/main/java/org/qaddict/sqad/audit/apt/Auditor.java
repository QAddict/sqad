package org.qaddict.sqad.audit.apt;

import jakarta.persistence.Entity;
import org.qaddict.sqad.audit.Exclude;
import org.qaddict.sqad.audit.Revision;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Stream.iterate;
import static javax.lang.model.util.ElementFilter.fieldsIn;
import static javax.lang.model.util.ElementFilter.typesIn;

@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class Auditor extends AbstractProcessor implements Consumer<TypeElement>, Predicate<TypeElement> {

    public static final String REVISION = "Revision";

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(Entity.class.getCanonicalName());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        typesIn(roundEnv.getElementsAnnotatedWith(Entity.class)).stream().filter(this).forEach(this);
        return false;
    }

    @Override
    public void accept(TypeElement element) {
        String pkg = processingEnv.getElementUtils().getPackageOf(element).toString();
        String name = element.getSimpleName() + REVISION;
        List<VariableElement> fields = allFields(element).filter(f -> isNull(f.getAnnotation(Exclude.class))).toList();
        //Set<String> imports = fields.stream().map(VariableElement::asType).filter(f -> !f.getKind().isPrimitive())
        //        .flatMap(m -> concat(Stream.of(m), ((DeclaredType)m).getTypeArguments().stream())).map(m -> processingEnv.getTypeUtils().asElement(m)).map(Object::toString).collect(toSet());
        try(PrintWriter writer = new PrintWriter(processingEnv.getFiler().createSourceFile(element.getQualifiedName() + REVISION, element).openWriter())) {
            writer.printf("""
                    package %s;
                    """, pkg);

            //imports.forEach(i -> writer.printf("import %s;\n", i));

            writer.printf("""
                    import jakarta.persistence.Entity;
                    
                    @Entity
                    public class %s {
                    """.stripIndent(),
                    name
            );

            fields.forEach(f -> {
                writer.println();
                f.getAnnotationMirrors().stream().filter(a -> !a.toString().contains("Revision")).forEach(a -> writer.println("\t" + a));
                if(nonNull(f.getAnnotation(Revision.class))) {
                    writer.println("\t@jakarta.persistence.Id");
                }
                writer.printf("\tprivate %1$s %2$s;", f.asType(), f.getSimpleName());
                writer.println();
            });

            fields.forEach(f -> writer.printf("""
                    
                    \tpublic %1$s get%3$s() {
                    \t    return %2$s;
                    \t}
                    
                    \tpublic %4$s set%3$s(%1$s %2$s) {
                    \t    this.%2$s = %2$s;
                    \t    return this;
                    \t}
                    """, f.asType(), f.getSimpleName(), cap(f.getSimpleName().toString()), name));

            writer.println("""
                    
                    }
                    """.stripIndent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String cap(String s) {
        return isNull(s) || s.isEmpty() ? s : s.substring(0,1).toUpperCase() + s.substring(1);
    }

    @Override
    public boolean test(TypeElement element) {
        return allFields(element).anyMatch(f -> nonNull(f.getAnnotation(Revision.class)));
    }

    private Stream<VariableElement> allFields(TypeElement element) {
        return iterate(element, Objects::nonNull, e -> (TypeElement) processingEnv.getTypeUtils().asElement(e.getSuperclass())).flatMap(e -> fieldsIn(e.getEnclosedElements()).stream());
    }

}
