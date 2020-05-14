# -*- coding: utf-8 -*-
"""
Created on Tue Apr 21 10:57:28 2020

@author: Rafael
"""


import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

partidas = pd.read_csv('picks_data.csv')
herois = pd.read_csv('herois.csv', index_col ="id")

def getVencedor(times):
    
    vencedorPossuiCarryEscape = False
    perdedorPossuiCarryEscape = False
        
    for h in range(0, 5):
        if ((herois.loc[times.iloc[h]]['roles/0'] == 'Carry' or herois.loc[times.iloc[h]]['roles/1'] == 'Carry' or herois.loc[times.iloc[h]]['roles/2'] == 'Carry')
        and (herois.loc[times.iloc[h]]['roles/0'] == 'Escape' or herois.loc[times.iloc[h]]['roles/1'] == 'Escape' or herois.loc[times.iloc[h]]['roles/2'] == 'Escape')):
            perdedorPossuiCarryEscape = True
                
    for h in range(5, 10):
       if ((herois.loc[times.iloc[h]]['roles/0'] == 'Carry' or herois.loc[times.iloc[h]]['roles/1'] == 'Carry' or herois.loc[times.iloc[h]]['roles/2'] == 'Carry')
        and (herois.loc[times.iloc[h]]['roles/0'] == 'Escape' or herois.loc[times.iloc[h]]['roles/1'] == 'Escape' or herois.loc[times.iloc[h]]['roles/2'] == 'Escape')):
            vencedorPossuiCarryEscape = True
                    
    if (vencedorPossuiCarryEscape and not perdedorPossuiCarryEscape):
        return 1
    elif (not vencedorPossuiCarryEscape and perdedorPossuiCarryEscape):
        return 0
    else:
        return -1
    
CarryEscapeVencedor = 0
CarryEscapePerdedor = 0
naoSeAplica = 0

for p in range(0,len(partidas)-1):
    resultado = getVencedor(partidas.iloc[p])
    if resultado == 1:
        CarryEscapeVencedor += 1
    elif resultado == 0:
        CarryEscapePerdedor += 1
    else: 
        naoSeAplica += 1
        
labels = ['Time Com Carry Escape Venceu', 
  'Time Sem Carry Escape Venceu']
quantidades = [CarryEscapeVencedor, CarryEscapePerdedor]

figura, ax = plt.subplots()
ax.pie(quantidades, labels = labels, autopct='%1.1f%%')
ax.axis('equal')
plt.show()