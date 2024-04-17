package com.leads.capita.service.instrument


import com.leads.capita.repository.DatabaseDriverFactory
import com.leads.capita.instrument.InstrumentRepository
import com.leads.capita.market.TickerService
import com.leads.capita.repository.instrument.InstrumentLocalRepositoryImpl
import com.leads.capita.repository.instrument.InstrumentRepositoryImpl
import com.leads.capita.service.RuntimeProfile
import com.leads.capita.service.RuntimeProfile.LIVE_RUNTIME


object InstrumentFactory {
//    fun getService(): TickerService {
//        return InstrumentServiceImpl()
//    }

//    fun getRepository(): InstrumentRepository{
//        if(RuntimeProfile.getCurrentRuntime() == LIVE_RUNTIME){
//            return InstrumentRepositoryImpl()
//        }else{
//            return  InstrumentLocalRepositoryImpl()
//        }
//    }
    fun getRepository(databaseDriverFactory: DatabaseDriverFactory): InstrumentRepository {
        return if(RuntimeProfile.getCurrentRuntime() == LIVE_RUNTIME){
            return InstrumentRepositoryImpl()
        }else{
            return  InstrumentLocalRepositoryImpl(databaseDriverFactory)
        }
    }
}