 #-------------------------------------------------------------------------------
# Name:         ENSC_Capstone_ShopSmart_GUI.py
# Purpose:      This script looks for a text file which gets updated every single time
# 				user scans a tag next to a rfid reader. Then it displays the content of the
#				text file by looking for that file every 100ms.
#               
#              
#              
#
# 
# Author:	  	Manpreet Singh    (301128525)
# Created:     12-10-2015
# Copyright:   ShopSmart 2015
# 
#
#-------------------------------------------------------------------------------

# library Import
from Tkinter import *
import Tkinter as tk
from store_items_database import*
import os
import platform


# Global variables
GUI_IMAGES_DIR		= os.path.join(os.getcwd(),"GUI_Images")
SHOP_SMART_LOGO_PATH    = os.path.join(GUI_IMAGES_DIR,"ShopSmart_no_dots.gif")
TEXT_FILE_RFID_PATH     = os.path.join(os.getcwd(),'scanned_tags_from_serial.txt')
TEXT_FILE_GUI_PATH      = os.path.join(os.getcwd(),"text_file_for_GUI_update.txt")

# For defining windows or linux dir
platform	        = platform.system()

if platform == 'Windows':
    SHOPPING_LIST_PATH      = os.path.join(os.getcwd(),'ShoppingList.txt')
else:
    SHOPPING_LIST_PATH      = r"/home/debian/Desktop/ShoppingList.txt"

# Font to be used for the text in GUI
TITLE_FONT              = ("Helvetica", 10, "bold")

IMPORTED_SHOPPING_LIST  = []



class StartShoppingPage(tk.Frame):

    def get_text(self,nameContent,fileName,listOfItems,priceContent,priceList,sumOfItemPrices,sumOfItemPricesContent):
         # try to open the file and set the value of val to its contents
        with open(fileName,"r") as file:
                file_content    = file.readlines()
                #-1 gets the last item
                lastItemOfTheList            = file_content[-1]
                temp2           = lastItemOfTheList.split(",")
                item_name       = temp2[0]
                item_price      = temp2[1]
                #print(item_price)
                if(not(listOfItems[-1]==item_name)):
                    listOfItems.append(item_name)
                    priceList.append(item_price)
                    pricesInFloat = (float(item_price))
                    sumOfItemPrices.append(pricesInFloat)
                    #print((sumOfItemPrices))
                    #print(priceOfItems)
                total = self.get_Shopping_total_from_list(sumOfItemPrices)
                nameContent.set(" ".join(listOfItems))
                priceContent.set(" ".join(priceList))
                sumOfItemPricesContent.set(total)

                #sumOfItemPricesContent.set(pricesInFloat)

        self.after(100, lambda:self.get_text(nameContent,fileName,listOfItems,priceContent,priceList,sumOfItemPrices,sumOfItemPricesContent))

    def __init__(self, parent, controller):

        tk.Frame.__init__(self, parent)

        label = tk.Label(self, text="Start Shopping", font=TITLE_FONT)
        label.pack(side="top", fill="x", pady=10)

        fileName = os.path.join(os.getcwd(),"TEXT_FILE_FOR_GUI.txt")
        nameContent = StringVar()
        priceContent = StringVar()
        sumOfItemPricesContent = StringVar()

        scroll = Scrollbar(self, orient = VERTICAL)

        nameOfTheItems = Listbox(self,yscrollcommand=scroll.set)
        scroll.config(command=nameOfTheItems.yview)
        nameOfTheItems.config(listvariable = nameContent,width = 28, height=15)
        scroll.pack(side=RIGHT,fill=Y)
        nameOfTheItems.pack(side = LEFT)

        priceOfTheItems = Listbox(self,yscrollcommand=scroll.set)
        priceOfTheItems.config(listvariable = priceContent,width = 28, height=15)
        priceOfTheItems.pack(side=LEFT,fill=Y)

        labelForTotalAmount = tk.Label(self, text="Total Amount($)")
        labelForTotalAmount.pack()


        Total = Entry(self, textvariable=sumOfItemPricesContent)
        Total.pack()

        buttonPro = tk.Button(self, text="CheckOut and Pay", command=lambda: controller.show_frame(PaymentPad))
        buttonPro.pack(pady=10)

        button = tk.Button(self, text="Go back to Main Menu", command=lambda: controller.show_frame(MainPage))
        button.pack(pady=10)


        itemsList = [""]
        priceList = [""]
        sumOfItemPrices = [""]
        self.get_text(nameContent,fileName,itemsList,priceContent,priceList,sumOfItemPrices, sumOfItemPricesContent)