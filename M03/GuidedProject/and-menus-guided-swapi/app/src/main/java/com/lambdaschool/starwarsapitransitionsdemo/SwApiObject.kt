package com.lambdaschool.starwarsapitransitionsdemo

import java.io.Serializable

abstract class SwApiObject(id: Int, name: String) : Serializable {
    var id: Int = 0
        protected set
    var category: String? = null
    var name: String
        protected set

    init {
        this.id = id
        this.name = name
    }

    abstract override fun toString(): String

    companion object {
        // TODO 8b: Move comparison to the objec
        fun compareNames(o1: SwApiObject, o2: SwApiObject) : Int{
            return o2.name.compareTo(o1.name)
        }
    }
}
