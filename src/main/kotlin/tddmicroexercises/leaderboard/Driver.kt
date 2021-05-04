package tddmicroexercises.leaderboard

open class Driver(open val name: String, val country: String) {


    override fun hashCode(): Int {
        return name.hashCode() * 31 + country.hashCode()
    }

    override fun equals(any: Any?): Boolean {
        return when{
            this === any -> true
            any == null || any !is Driver -> false
            else -> {
                val another = any as Driver?
                return this.name == another!!.name && this.country == another.country
            }
        }
    }
}
