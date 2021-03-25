package SubPrjct.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.ParameterDisplay

object SubPrjct_Build1 : BuildType({
    templates(_Self.buildTypes.sampleProjectTemplate)
    name = "Build from template"

    params {
        text("system.build.anothervar", "value", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        checkbox("system.drink", "true", label = "Coffee?", checked = "true", unchecked = "false")
    }


    requirements {
        matches("teamcity.agent.name", "MyAwesomeAgent")
    }
})
