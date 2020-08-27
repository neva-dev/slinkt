package com.neva.slinkt.script.engine

import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.SlingHttpServletResponse
import org.apache.sling.api.servlets.ServletResolverConstants
import org.apache.sling.api.servlets.SlingSafeMethodsServlet
import org.osgi.service.component.annotations.Component
import org.osgi.service.component.annotations.Reference
import javax.servlet.Servlet

@Component(
  service = [Servlet::class],
  immediate = true,
  property = [
    ServletResolverConstants.SLING_SERVLET_PATHS + "=/bin/slinkt/script/engine",
    ServletResolverConstants.SLING_SERVLET_METHODS + "=GET",
  ]
)
class KotlinEngineServlet : SlingSafeMethodsServlet() {

  @Reference
  private lateinit var factory: KotlinScriptEngineFactory

  override fun doGet(request: SlingHttpServletRequest, response: SlingHttpServletResponse) {
    val engine = factory.scriptEngine
    val result = engine.eval("""
      val a = "Hello World!"
      System.out.println(a)
    """.trimIndent())

    response.contentType = "text/plain"
    response.writer.write(result.toString())
  }
}
