package DAO;

import beans.Comida;
import java.util.ArrayList;
import java.util.List;

public class ComidaDAO {
    private List<Comida> comidas = new ArrayList<>();
    
    public void adicionar(Comida comida) {
        comidas.add(comida);
    }
    
    public List<Comida> listar() {
        return comidas;
    }
}