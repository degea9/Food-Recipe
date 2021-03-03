object Modules {

    const val APP = ":app"
    val featureHome = ":features:home"
    val featureFavorite = ":features:favorite"
    val featureCategoryRecipes = ":features:category_recipes"
    val featureRecipeDetail = ":features:recipe-detail"
    val featureSearch = ":features:search"
    val repository = ":data:repository"
    val remote = ":data:remote"
    val domain = ":domain"
    val core = ":core"
    val common_android = ":common-android"

    object AndroidLibrary {
        const val CORE = ":libraries:core"
        const val DATA = ":libraries:data"
        const val DOMAIN = ":libraries:domain"
        val COMMON_ANDROID = ":common-android"
        const val TEST_UTILS = ":libraries:test-utils"
    }

    /**
     * Dynamic Feature Modules
     */
    object DynamicFeature {

    }
}