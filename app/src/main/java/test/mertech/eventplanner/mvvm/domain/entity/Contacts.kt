package test.mertech.eventplanner.mvvm.domain.entity

data class Contacts (
    val id: Int = UNDEFINED_ID,
    val name: String,
    val surName:String,
    val jobTitle: String,
    val phoneNumber: String
) {

    companion object {

        const val UNDEFINED_ID = 0
    }
}