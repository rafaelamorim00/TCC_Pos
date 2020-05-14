# -*- coding: utf-8 -*-
"""
Created on Wed Apr 15 21:37:40 2020

@author: Rafael
"""

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

partidas = pd.read_csv('picks_data.csv')
herois = pd.read_csv('herois.csv', index_col ="id")

def getVencedor(times):
    
    antimageVencedor = -1
    quantidadeInt = 0
    
    for h in range(0, 5):
        if (times.iloc[h] == 1):
            antimageVencedor = 0
    for h in range(5, 10):
        if (times.iloc[h] == 1):
            antimageVencedor = 1
    
    if antimageVencedor >= 0:
        if antimageVencedor == 1:
            for h in range(0, 5):
                if herois.loc[times.iloc[h]]['primary_attr'] == 'int':
                    quantidadeInt += 1
        if antimageVencedor == 0:
            for h in range(5, 10):
                if herois.loc[times.iloc[h]]['primary_attr'] == 'int':
                    quantidadeInt += 1
                    
    if quantidadeInt >= 3:
        return antimageVencedor
    else:
        return -1
    
AM_maisVencedor = 0
AM_maisPerdedor = 0
naoSeAplica = 0

for p in range(0,len(partidas)-1):
    resultado = getVencedor(partidas.iloc[p])
    if resultado == 1:
        AM_maisVencedor += 1
    elif resultado == 0:
        AM_maisPerdedor += 1
    else: 
        naoSeAplica += 1
        
labels = ['Time Com Anti-Mage Venceu', 
  'Time com Três ou Mais Heróis de Inteligência Venceu']
quantidades = [AM_maisVencedor, AM_maisPerdedor]

figura, ax = plt.subplots()
ax.pie(quantidades, labels = labels, autopct='%1.1f%%')
ax.axis('equal')
plt.show()