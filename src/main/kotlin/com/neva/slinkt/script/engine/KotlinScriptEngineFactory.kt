package com.neva.slinkt.script.engine

import org.apache.sling.scripting.api.AbstractScriptEngineFactory
import org.osgi.service.component.annotations.Component
import javax.script.ScriptEngine
import javax.script.ScriptEngineFactory

@Component(
  immediate = true,
  service = [ScriptEngineFactory::class, KotlinScriptEngineFactory::class]
)
class KotlinScriptEngineFactory : AbstractScriptEngineFactory() {

  private val factory = KotlinEngineFactory()

  override fun getLanguageName() = "kts"

  override fun getLanguageVersion(): String = "1.4"

  override fun getScriptEngine(): ScriptEngine = factory.scriptEngine
}
