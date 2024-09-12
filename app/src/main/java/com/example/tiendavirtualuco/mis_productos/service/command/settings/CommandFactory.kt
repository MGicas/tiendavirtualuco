package com.example.tiendavirtualuco.mis_productos.service.command.settings

import android.content.Context
import com.example.tiendavirtualuco.mis_productos.service.command.Command
import com.example.tiendavirtualuco.mis_productos.service.command.CommandsViewsEnum
import com.example.tiendavirtualuco.mis_productos.service.views.OpenFavoritesCommand
import com.example.tiendavirtualuco.mis_productos.service.views.OpenMyproductsCommand

object CommandFactory {
    fun createCommandFavorites(type: CommandsViewsEnum, context: Context): Command {
        return when (type) {
            CommandsViewsEnum.OPEN_FAVORITES -> OpenFavoritesCommand(context)
            //TODO: Add more commands

            else -> throw IllegalArgumentException("Comando desconocido")
        }
    }
    fun createCommandMyProducts(type: CommandsViewsEnum, context: Context): Command {
        return when (type) {
            CommandsViewsEnum.OPEN_MY_PRODUCTS -> OpenMyproductsCommand(context)
            //TODO: Add more commands

            else -> throw IllegalArgumentException("Comando desconocido")
        }
    }
}