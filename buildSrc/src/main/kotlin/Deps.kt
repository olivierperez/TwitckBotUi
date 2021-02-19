object Deps {

    private const val moshiVersion = "1.9.3"
    private const val ktorVersion = "1.5.0"
    private const val logbackVersion = "1.2.1"

    val moshi = arrayOf(
        "com.squareup.moshi:moshi:$moshiVersion",
        "com.squareup.moshi:moshi-adapters:$moshiVersion",
        "com.squareup.moshi:moshi-kotlin:$moshiVersion"
    )
    val moshiKapt = "com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion"

    val ktor = arrayOf(
        "io.ktor:ktor-server-netty:$ktorVersion",
        "io.ktor:ktor-client-core:$ktorVersion",
        "io.ktor:ktor-client-core-jvm:$ktorVersion",
        "io.ktor:ktor-client-cio:$ktorVersion",
        "io.ktor:ktor-server-core:$ktorVersion",
        "io.ktor:ktor-websockets:$ktorVersion",
        "io.ktor:ktor-client-websockets:$ktorVersion"
    )
    // TODO testImplementation("io.ktor:ktor-server-tests:$ktorVersion")

    val log = arrayOf(
        "ch.qos.logback:logback-classic:$logbackVersion"
    )

}
