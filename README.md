# Hospital_Location_Optimizer

- Este repositório contém a implementação de um programa que resolve o problema clássico de otimização conhecido como “Problema de Localização de Instalações”. O programa determina o melhor local para uma instalação, como um hospital, com base em demandas geográficas, custos e distâncias envolvidas.

- O objetivo do programa é minimizar a distância máxima percorrida pelos habitantes de uma cidade para chegar ao hospital. Para isso, ele considera um grafo onde os pesos representam a distância entre dois nós e determina a localização que minimiza a distância máxima para qualquer outro nó do grafo. A distância máxima de um vértice v em um grafo G é denominada de excentricidade, definida como:

- <math xmlns="http://www.w3.org/1998/Math/MathML" display="block"><semantics><mrow><mtext>exc</mtext><mo stretchy="false">(</mo><mi>v</mi><mo stretchy="false">)</mo><mo>=</mo><mi>max</mi><mo>⁡</mo><mo stretchy="false">{</mo><mtext>dist</mtext><mo stretchy="false">(</mo><mi>v</mi><mo separator="true">,</mo><mi>x</mi><mo stretchy="false">)</mo><mi mathvariant="normal">∣</mi><mi>x</mi><mo>∈</mo><mi>V</mi><mo stretchy="false">(</mo><mi>G</mi><mo stretchy="false">)</mo><mo stretchy="false">}</mo></mrow><annotation encoding="application/x-tex">\text{exc}(v) = \max\{\text{dist}(v, x) | x \in V(G)\}</annotation></semantics></math>
