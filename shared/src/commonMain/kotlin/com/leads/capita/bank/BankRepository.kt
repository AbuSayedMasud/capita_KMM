package com.leads.capita.bank

interface BankRepository {
    fun getBankList(): String
    fun getBranchList(bankId:Int): String
}