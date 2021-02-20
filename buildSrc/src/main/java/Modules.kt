object Modules {

    const val APP = ":app"
    val featureHome = ":features:home"
    val featureCategoryRecipes = ":features:category_recipes"
    val featureDetail = ":features:detail"
    val repository = ":data:repository"
    val remote = ":data:remote"
    val domain = ":domain"
    val core = ":core"

    object AndroidLibrary {
        const val CORE = ":libraries:core"
        const val DATA = ":libraries:data"
        const val DOMAIN = ":libraries:domain"
        const val TEST_UTILS = ":libraries:test-utils"
    }

    /**
     * Dynamic Feature Modules
     */
    object DynamicFeature {

    }
}