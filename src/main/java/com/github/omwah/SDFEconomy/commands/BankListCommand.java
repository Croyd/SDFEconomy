package com.github.omwah.SDFEconomy.commands;

import com.github.omwah.SDFEconomy.BankAccount;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.github.omwah.SDFEconomy.SDFEconomyAPI;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;
import org.bukkit.entity.Player;

public class BankListCommand extends PlayerSpecificCommand
{
    public BankListCommand(SDFEconomyAPI api)
    {
        super("bank list", api);
        
        setDescription("List bank accounts");
        setUsage(this.getName() + " §8[owner] [location]");
        setArgumentRange(0, 2);
        setIdentifiers(this.getName());
        setPermission("sdfeconomy.use_bank");
    }

    @Override
    public boolean execute(CommandHandler handler, CommandSender sender, String label, String identifier, String[] args)
    {
        PlayerAndLocation ploc = getPlayerAndLocation(handler, sender, args, 0, 1);

        if(ploc != null) {
            List<BankAccount> bank_accounts;
            boolean display_admin = false;
            if (handler.hasAdminPermission(sender)) {
                // Get all banks for a location
                bank_accounts = api.getAllBanks();
                display_admin = true;
            } else {
                // Only report those owned by player unless sender is op or console
                bank_accounts = api.getPlayerBanks(ploc.playerName, ploc.locationName);
            }
            
            sender.sendMessage("§c-----[ " + "§f Bank Accounts §c ]-----");
            for(BankAccount account : bank_accounts) {
                if (display_admin) {
                    sender.sendMessage(account.getName() + " @ " + account.getLocation() + " : " + account.getOwner());
                } else {
                    sender.sendMessage(account.getName());
                }
            }
        } else {
            // Unable to succesfully get player name and or location, helper routine will send appropriate message
            return false;
        }
            
        return true;
    }
   
}