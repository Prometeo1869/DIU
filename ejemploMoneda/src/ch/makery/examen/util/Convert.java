package ch.makery.examen.util;

import Modelo.MonedaVO;
import ch.makery.examen.model.Moneda;

public class Convert {

    public static Moneda convertTo(MonedaVO mvo) {
        Moneda m = new Moneda(
                mvo.getNombre(),
                mvo.getMultiplicador(),
                mvo.getCodigo()
        );
        return m;
    }
    public static MonedaVO convertTo(Moneda m) {
        MonedaVO mvo = new MonedaVO(
                m.getNombre(),
                m.getMultiplicador(),
                m.getCodigo()
        );
        return mvo;
    }
}
