package com.github.fahdarhalai.dac.annotation.processor;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("com.github.fahdarhalai.dac.annotation.Partition")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class PartitionAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("HELLO THERE !");
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "HELLO THERE !");
        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
            annotatedElements.forEach(element -> {
                processingEnv.getMessager()
                        .printMessage(Diagnostic.Kind.NOTE, "Found one annotated class " + element.getClass());
            });
            try {
                throw new Exception("damn");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }
}
