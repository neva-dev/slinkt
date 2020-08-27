package com.neva.slinkt.script.host

import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.SlingHttpServletResponse
import org.apache.sling.api.servlets.ServletResolverConstants
import org.apache.sling.api.servlets.SlingSafeMethodsServlet
import org.osgi.service.component.annotations.Component
import org.osgi.service.component.annotations.Reference
import javax.servlet.Servlet
import kotlin.script.experimental.api.valueOrNull
import kotlin.script.experimental.host.StringScriptSource

@Component(
  service = [Servlet::class],
  immediate = true,
  property = [
    ServletResolverConstants.SLING_SERVLET_PATHS + "=/bin/slinkt/script/evaluator",
    ServletResolverConstants.SLING_SERVLET_METHODS + "=GET",
  ]
)
class SimpleServlet : SlingSafeMethodsServlet() {

  @Reference
  private lateinit var evaluator: KotlinScriptEvaluator

  override fun doGet(request: SlingHttpServletRequest, response: SlingHttpServletResponse) {
    val scriptSource = StringScriptSource("""
      println("Hello World!")
      "abecadlo!"
    """.trimIndent())

    val result = evaluator.eval(scriptSource)

    response.contentType = "text/plain"
    response.writer.write(result.valueOrNull().toString())
  }
}
