package com.cognifide.slinkt

import org.apache.sling.scripting.api.AbstractScriptEngineFactory
import org.jetbrains.kotlin.script.jsr223.KotlinJsr223JvmLocalScriptEngineFactory
import org.osgi.service.component.annotations.Activate
import org.osgi.service.component.annotations.Component
import org.slf4j.LoggerFactory
import javax.script.ScriptEngine
import javax.script.ScriptEngineFactory

@Component(
  immediate = true,
  service = [ScriptEngineFactory::class]
)
class KotlinScriptEngineFactory : AbstractScriptEngineFactory() {

  override fun getLanguageName() = "kts"

  override fun getLanguageVersion(): String = "1.4"

  override fun getScriptEngine(): ScriptEngine {
    val factory = KotlinJsr223JvmLocalScriptEngineFactory() // load it somehow and reuse
    return factory.scriptEngine
  }

  @Activate
  protected fun activate() {
    val result = scriptEngine.eval("""
      println("Hello World!")
    """.trimIndent())

    LOG.info("Kotlin eval result", result)
  }

  companion object {
    val LOG = LoggerFactory.getLogger(KotlinScriptEngineFactory::class.java)
  }
}
