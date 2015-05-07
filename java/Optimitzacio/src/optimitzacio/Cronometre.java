package optimitzacio;

/**
 * Classe cronometre que permet fer un conteig dels milisegons que passen des de
 * que s'inicialitza, fins que para.
 * @author valarcon
 */
public class Cronometre {
    
    private long num1;
    private long num2;
    
    /**
     * Constructora que no rep parametres, no els necessitem.
     */
    public Cronometre(){
        this.num1=0;
        this.num2=0;
    }
    
    /**
     * El metode Start, realment, no incia un comptador, si no que agafa la data
     * del sistema.
     */
    public void Start(){
        num1 = System.nanoTime();
    }
    
    /**
     * Stop fa el mateix que Start pero declara el nombre a num2.
     */
    public void Stop(){
        num2 = System.nanoTime();
    }
    
    /**
     * La funció de retorn resta els nombres donats a Start i Stop i el divideix
     * per 1000000 per poder retornar milisegons.
     * @return
     */
    public Long EllapsedMilliseconds(){
        return (num2 - num1)/1000000;
    }
    
    /**
     * Restart només torna a reincialitzar els parametres a 0.
     */
    public void Restart(){
        num1=0;
        num2=0;
    }
}