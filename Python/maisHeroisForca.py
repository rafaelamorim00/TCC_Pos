# -*- coding: utf-8 -*-
"""
Created on Wed Apr 15 20:37:37 2020

@author: Rafael
"""

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

partidas = pd.read_csv('picks_data.csv')
herois = pd.read_csv('herois.csv', index_col ="id")

def getTimeMaiorForca(times):
    vencedoresForca = 0
    perdedoresForca = 0
    
    for h in range(0, 5):
        if herois.loc[times.iloc[h]]['primary_attr'] == 'str':
            perdedoresForca += 1
    
    for h in range(5, 10):
        if herois.loc[times.iloc[h]]['primary_attr'] == 'str':
            vencedoresForca += 1
            
    if vencedoresForca > perdedoresForca:
        return 1
    elif perdedoresForca > vencedoresForca:
        return -1
    else:
        return 0

timeMaisForcaVencedor = 0
timeMaisForcaPerdedor = 0
quantidadesIguais = 0

for p in range(0,len(partidas)-1):
    resultado = getTimeMaiorForca(partidas.iloc[p])
    if resultado == 1:
        timeMaisForcaVencedor += 1
    elif resultado == -1:
        timeMaisForcaPerdedor += 1
    else: 
        quantidadesIguais += 1
            
labels = ['Time Vencedor com Mais Heróis de Força', 
          'Time Perdedor com Mais Heróis de Força', 
          'Times com a Mesma Quantidade']
quantidades = [timeMaisForcaVencedor, timeMaisForcaPerdedor, quantidadesIguais]

figura, ax = plt.subplots()
ax.pie(quantidades, labels = labels, autopct='%1.1f%%')
ax.axis('equal')
plt.show()