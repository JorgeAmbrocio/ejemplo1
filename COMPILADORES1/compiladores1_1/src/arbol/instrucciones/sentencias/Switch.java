/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.instrucciones.sentencias;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.entorno.Entorno;
import arbol.entorno.Tipo;
import arbol.expresiones.relacionales.Igual;
import arbol.instrucciones.Break;
import arbol.instrucciones.Continue;
import arbol.instrucciones.Return;
import compiladores1_1.Compiladores1_1;
import interfaz.Errores;
import java.util.LinkedList;

/**
 *
 * @author David Ventura
 */
public class Switch extends Instruccion {

    Expresion valor;
    LinkedList <CondicionSwitch> condiciones;
    boolean ejecutado;
    public Switch(Expresion valor, LinkedList<CondicionSwitch> condiciones) {
        
        this.valor = valor;
        this.condiciones = condiciones;
        this.ejecutado = false;
        
    }
    
    
    @Override
    public Object ejecutar(Entorno ent) {
        compiladores1_1.Compiladores1_1.pilaCiclos.add(Compiladores1_1.enumCiclo.Switch);
        Object retorno=null;
        this.ejecutado = false;
        for (CondicionSwitch condicion : this.condiciones) {
            // recorre todos los casos
            
            // sin poner atención a los default
            if (condicion.valorCaso != null && !this.ejecutado) {
                // se queda solo con los que no son default
                // valida si la condición es igual
                Igual igual = new Igual (this.valor, condicion.valorCaso);
                Expresion igual_ = igual.getValor(new Entorno (ent));
                
                // valida que se haya podido realizar la igualación
                if (igual_.tipo.tipo == Tipo.EnumTipo.booleano) {
                    // sí se pudo realizar la igualación
                    // verifica que sea verdadera
                    boolean blnIgual = Boolean.parseBoolean(igual_.valor.toString());
                    
                    if (blnIgual ) {
                        // si la condición es real, se ejecuta
                        this.ejecutado  = true;
                        retorno = condicion.ejecutar(new Entorno(ent));
                        
                        if (retorno != null) {
                            // encontró un break o un continue
                            if(retorno.getClass() == Break.class) {
                                // retornar null
                                retorno = null;
                                break;
                            }else if (retorno.getClass() == Continue.class) {
                                // si es contienue retorna el continue
                                break;
                            }else if (retorno.getClass() == Return.class) {
                                // si viene un return, salir del switch y retornar lo que trae
                                break;
                            }
                        }
                    }
                } else {
                    System.out.println("Se esperaba valor booleano en el switch\n");
                    Errores e = new Errores (Errores.enumTipoError.semantico , "Se esperaba valor booleano en el switch");
                }
                
            }else if ( this.ejecutado)  {
                // si es un default
                // pero ya se ejecutó algun case
                retorno = condicion.ejecutar(new Entorno(ent));
                
                if (retorno != null) {
                    // significa que encontró n brreak o un continue
                    
                    if (retorno.getClass() == Break.class) {
                        // el switch nunca retorna un break
                        retorno = null;
                        break;
                    }else if (retorno.getClass() == Continue.class) {
                        // si viene un continue, se sale del switch
                        
                        break;
                    } else if (retorno.getClass() == Return.class) {
                        // viene return, retornar lo que trae
                        break;
                    }
                }
            }
        }
        
        if (!this.ejecutado) {
            // si no se pudo ejecutar ningun caso
            // buscar primer defautl
            
            for (CondicionSwitch condicion : this.condiciones) {
                // recorre toda la lista de caosos again
                
                if (condicion.valorCaso == null) {
                    // es un default
                    this.ejecutado = true;
                    retorno = condicion.ejecutar(new Entorno(ent));
                    if (retorno != null) {
                           // significa que encontró n brreak o un continue

                        if (retorno.getClass() == Break.class) {
                            // el switch nunca retorna un break
                            retorno = null;
                            break;
                        }else if (retorno.getClass() == Continue.class) {
                            // si viene un continue, se sale del switch
                            break;
                        } else if (retorno.getClass() == Return.class) {
                            // viene return, retornar lo que trae
                            break;
                        }
                    } 
                }else if (this.ejecutado) {
                    // ya tengo que ejecutar todo
                    retorno = condicion.ejecutar(new Entorno(ent));
                    if (retorno != null) {
                           // significa que encontró n brreak o un continue

                        if (retorno.getClass() == Break.class) {
                            // el switch nunca retorna un break
                            retorno = null;
                            break;
                        }else if (retorno.getClass() == Continue.class) {
                            // si viene un continue, se sale del switch
                            break;
                        } else if (retorno.getClass() == Return.class) {
                            // viene return, retornar lo que trae
                            break;
                        }
                    }
                }   
            }
        }
        
        
        compiladores1_1.Compiladores1_1.pilaCiclos.pollLast();
        return retorno;
    }
    
    
    
}
