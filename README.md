From an old, old blog post on [my blog](http://www.objectify.be/wordpress/2009/11/09/adding-arbitrary-parameters-to-a-url-in-struts2/)

When building a Struts2 application recently, I needed to add arbitrary parameters to a URL when creating the menu from dynamic content. The parameters were stored in a map, so I used the my standard bit of code for iterating over a map:

    <s:url var="url" action="%{link}" >
      <s:iterator value="parameters.keySet()" var="key">
        <s:param name="%{key}" value="%{parameters.get(#key)}"/>
      </s:iterator>
    </s:url>

…and nothing happened. No parameters at all appeared in the URL.

Odd.

I got rid of the iterator and used a single parameter, just to check:

    <s:url var="url" action="%{link}" >
      <s:param name="test-name" value="test-value"/>
    </s:url>

That worked fine. One quick debugging session later and I found the problem – the Struts2 org.apache.struts2.components.Param component parameterises its parent component. In this case, the parent component is an iterator and so it was absorbing the parameters and they were never getting as far as the URL.

I couldn’t find a way to do what I needed the core Struts2 components and tags and so I created my own.

IterableParam overrides Param’s findAncestor method to return the grandparent component in the case where the parent is an Iterator:

    <s:url var="url" action="%{link}" >
      <s:iterator value="parameters.keySet()" var="key">
        <ob:iterable-param name="%{key}" value="%{parameters.get(#key)}"/>
      </s:iterator>
    </s:url>
    
Result – works as required.

Despite the title of this blog entry, any Struts2 component that can be parameterised using the <s:param> tag can be parameterised using <ob:iterable-param>.
