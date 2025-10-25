package pe.com.master.machines.data.mappers

import pe.com.master.machines.model.model.Info
import pe.com.master.machines.network.model.model.InfoNetwork

fun InfoNetwork.asModel() = Info(
    count = this.count ?: -1,
    next = this.next.orEmpty(),
    pages = this.pages ?: -1,
    prev = this.prev.orEmpty(),
)