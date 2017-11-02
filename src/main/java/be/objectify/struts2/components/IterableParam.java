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
package be.objectify.struts2.components;

import com.opensymphony.xwork2.util.ValueStack;

import org.apache.struts2.components.Component;
import org.apache.struts2.components.Param;
import org.apache.struts2.components.IteratorComponent;

import java.util.Stack;

/**
 * @author Steve Chaloner
 * @version <!-- $Revision$ -->, <!-- $Date$ -->
 */
public class IterableParam extends Param
{
    /**
     * Initialises a new instance of this class.
     *
     * @param stack the value stack
     */
    public IterableParam(final ValueStack stack)
    {
        super(stack);
    }

    /**
     * Gets the first ancestor of the component except in the case where
     * the ancestor is an iterator.  In this case, the ancestor of the iterator
     * is located.  This second ancestor may be an iterator.
     *
     * @param clazz the class of the ancestor to find
     * @return the first ancestor of this component, or the ancestor's ancestor in the case
     *         where the first ancestor is an iterator.
     */
    @Override
    protected Component findAncestor(final Class clazz)
    {
        Component ancestor = super.findAncestor(clazz);
        if (ancestor != null && ancestor instanceof IteratorComponent)
        {
            final Stack componentStack = ancestor.getComponentStack();
            int currPosition = componentStack.search(ancestor);
            if (currPosition >= 0)
            {
                final int start = componentStack.size() - currPosition - 1;
                boolean found = false;
                for (int i = start; !found && i >= 0; i--)
                {
                    final Component component = (Component) componentStack.get(i);
                    if (clazz.isAssignableFrom(component.getClass()) && component != this)
                    {
                        ancestor = component;
                        found = true;
                    }
                }
            }
        }
        return ancestor;
    }
}
