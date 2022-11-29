package test.mertech.eventplanner.mvvm.domain.entity

data class Event(
    val id: Int = UNDEFINED_ID,
    val title: String,
    val description: String,
    val date: String,
    val country: String,
    val city: String,
    val street: String,
    val house: String,
    val blocks: String? = null,
    val status: String,
    val imageUrl: String? = null
) {

    companion object {

        const val UNDEFINED_ID = 0
    }
}