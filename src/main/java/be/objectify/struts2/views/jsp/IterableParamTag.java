/*
 * Copyright 2009 Steve Chaloner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package be.objectify.struts2.views.jsp;

import com.opensymphony.xwork2.util.ValueStack;

import org.apache.struts2.views.jsp.ParamTag;
import org.apache.struts2.components.Component;

import be.objectify.struts2.components.IterableParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Steve Chaloner
 * @version <!-- $Revision$ -->, <!-- $Date$ -->
 */
public class IterableParamTag extends ParamTag
{
    /** {@inheritDoc} */
    public Component getBean(ValueStack stack,
                             HttpServletRequest req,
                             HttpServletResponse res) {
        return new IterableParam(stack);
    }
}
