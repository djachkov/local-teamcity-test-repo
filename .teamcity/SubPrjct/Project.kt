package SubPrjct

import SubPrjct.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.ParameterDisplay
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("SubPrjct")
    name = "Sample Sub Project"

    buildType(SubPrjct_Build1)

    params{
        text("system.somevar", "TEXT", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }
})
