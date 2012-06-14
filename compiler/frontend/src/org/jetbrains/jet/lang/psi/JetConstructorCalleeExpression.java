/*
 * Copyright 2010-2012 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.jet.lang.psi;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author abreslav
 */
public class JetConstructorCalleeExpression extends JetExpressionImpl {
    public JetConstructorCalleeExpression(@NotNull ASTNode node) {
        super(node);
    }

    @Nullable @IfNotParsed
    public JetTypeReference getTypeReference() {
        return findChildByClass(JetTypeReference.class);
    }

    @Nullable @IfNotParsed
    public JetReferenceExpression getConstructorReferenceExpression() {
        JetTypeReference typeReference = getTypeReference();
        if (typeReference == null) {
            return null;
        }
        JetTypeElement typeElement = typeReference.getTypeElement();
        if (typeElement == null) {
            return null;
        }
        assert typeElement instanceof JetUserType : typeElement;
        return ((JetUserType) typeElement).getReferenceExpression();
    }

}
