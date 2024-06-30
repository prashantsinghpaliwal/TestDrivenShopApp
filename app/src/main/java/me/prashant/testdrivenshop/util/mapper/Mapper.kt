package me.prashant.testdrivenshop.util.mapper

interface Mapper<I, O> {
    fun convert(input: I): O
}