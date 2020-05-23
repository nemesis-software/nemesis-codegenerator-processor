/*
 * nemesis Platform - NExt-generation Multichannel E-commerce SYStem
 *
 * Copyright (c) 2010 - 2020 nemesis
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of nemesis
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with nemesis.
 */
package io.nemesis.platform.codegenerator.processor;

import com.helger.jcodemodel.JAnnotationUse;
import com.helger.jcodemodel.JDefinedClass;
import com.nemesis.platform.core.mixin.codegen.codemodel.JCodeModel;
import com.nemesis.platform.core.mixin.codegen.hooks.NemesisClassCodeGeneratorPostProcessor;
import org.hibernate.annotations.Cache;

import java.util.Collection;

/**
 * A sample implementation of {@code NemesisClassCodeGeneratorPostProcessor}. Use it as a foundation to build
 * your own post processor.
 *
 * @author Petar Tahchiev
 * @since 2.2.2
 */
public class SampleCodeGeneratorProcessor implements NemesisClassCodeGeneratorPostProcessor {

    /**
     * An example code generator processor - will annotate the UserEntity with a new annotation, and will
     * also change the @Cache annotation 'include' parameter from the title field of the UserEntity.
     *
     * @param claz
     * @param codeModel
     */
    @Override
    public void process(JDefinedClass claz, JCodeModel codeModel) {
        if (claz.fullName().endsWith("UserEntity")) {
            claz.annotate(SampleAnnotation.class);
            Collection<JAnnotationUse> annotations = claz.fields().get("title").annotations();
            for (JAnnotationUse annotation : annotations) {
                if (annotation.getAnnotationClass().fullName().endsWith(Cache.class.getCanonicalName())) {
                    annotation.param("include", "non-lazy");
                }
            }
        }
    }
}
