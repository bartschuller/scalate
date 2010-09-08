/**
 * Copyright (C) 2009-2010 the original author or authors.
 * See the notice.md file distributed with this work for additional
 * information regarding copyright ownership.
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

package org.fusesource.scalate.wikitext

import java.io.File

/**
 * Test cases for {snippet} macro support 
 */
class SnippetsTest extends AbstractConfluenceTest {

  // setting up a snippet prefix 'test' -> 'src/test/resources'
  Snippets.addPrefix("test", new File(baseDir, "src/test/resources").toURI.toString)

  test("snippets macro without snippet id") {

    assertFilter(
"""
h1. Full snippet here
{snippet:url=test/Test.java}
""",
"""<h1 id="Fullsnippethere">Full snippet here</h1><div class="snippet"><pre class="java">
/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * A simple Test class
 */
public class Test {

    // START SNIPPET: doSomething
    public void doSomething() {
        // does something very interesting
    }
    // END SNIPPET: doSomething

    public void doSomethingElse() {
        // does something else, even more interesting
    }

}
</pre></div>""")
  }

  test("snippets macro with snippet id") {

    assertFilter(
"""
h1. Snippet with id
{snippet:url=test/Test.java|id=doSomething}
""",
"""<h1 id="Snippetwithid">Snippet with id</h1><div class="snippet"><pre class="java">
    public void doSomething() {
        // does something very interesting
    }
</pre></div>""")
  }

  test("snippets macro with snippet id and explicit language") {

    assertFilter(
"""
h1. Snippet with id
{snippet:url=test/Test.java|id=doSomething|lang=erlang}
""",
"""<h1 id="Snippetwithid">Snippet with id</h1><div class="snippet"><pre class="erlang">
    public void doSomething() {
        // does something very interesting
    }
</pre></div>""")
  }

  test("snippets macro with relative url and snippet id") {

    assertFilter(
"""
h1. Snippet with id
{snippet:url=src/test/resources/Test.java|id=doSomething}
""",
"""<h1 id="Snippetwithid">Snippet with id</h1><div class="snippet"><pre class="java">
    public void doSomething() {
        // does something very interesting
    }
</pre></div>""")
  }
}