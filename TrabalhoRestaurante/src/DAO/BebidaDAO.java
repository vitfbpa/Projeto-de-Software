package DAO;

import beans.Bebida;
import java.util.ArrayList;
import java.util.List;

public class BebidaDAO {
    private List<Bebida> bebidas = new ArrayList<>();
    
    public void adicionar(Bebida bebida) {
        bebidas.add(bebida);
    }
    
    public List<Bebida> listar() {
        return bebidas;
    }
}