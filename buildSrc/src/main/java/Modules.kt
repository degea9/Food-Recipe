object Modules {

    const val APP = ":app"
    val featureHome = ":features:home"
    val featureCategoryRecipes = ":features:category_recipes"
    val featureRecipeDetail = ":features:recipe-detail"
    val repository = ":data:repository"
    val remote = ":data:remote"
    val domain = ":domain"
    val core = ":core"
    val common_android = ":common-android"

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