package br.com.ubibus.model.pgconverter;

import java.sql.SQLException;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;
import org.postgis.*;
import org.postgresql.util.PGobject;

/**
 * Esta classe permite a conversão de dados provenientes de um banco de dados
 * PostGIS para objetos java e vice-versa.
 *
 * @see Converter
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 */
public class CustomConverter implements Converter {

    /**
     * Convete dados do postGIS em um objeto java.
     *
     * @param dataValue - objeto contendo os dados vindos do PostGIS.
     * @param session - sessão a partir da qual os dados foram obtidos.
     * @return um objeto {@link Geometry} que pode ser utilizado no java. O
     * retorno pode ser {@code null} caso o objeto {@code dataValue} seja nulo
     * ou caso o mesmonão contenha dados PostGIS.
     *
     */
    @Override
    public Geometry convertDataValueToObjectValue(Object dataValue, Session session) {
        if (dataValue == null) {
            return null;
        } else if (dataValue instanceof PGgeometry) {
            return ((PGgeometry) dataValue).getGeometry();
        } else if (dataValue instanceof PGobject) {
            PGobject pgo = (PGobject) dataValue;
            PGgeometry geom = new PGgeometry();
            try {
                geom.setValue(pgo.getValue());
            } catch (SQLException ex) {
                System.err.println("Erro ao converter dados em objeto: " + ex.getMessage());
            }
            return geom.getGeometry();
        } else {
            return null;
        }
    }

    /**
     * Convete um objeto java em dados compativeis com o PostGIS, porem este
     * objeto deve ser do tipo {@link Geometry}.
     *
     * @param objectValue - objeto a ser convertido.
     * @param session - sessão à qual os dados extraidos do objeto {@code objectValue}
     * serão adicionados.
     * @return um objeto {@link PGgeometry} contendo os dados extraídos de {@code objectValue}.
     */
    @Override
    public PGgeometry convertObjectValueToDataValue(Object objectValue, Session session) {
        if (objectValue == null) {
            return null;
        } else if (objectValue instanceof Geometry) {
            return new PGgeometry((Geometry) objectValue);
        } else if (objectValue instanceof Point) {
            return new PGgeometry((Point) objectValue);
        } else if (objectValue instanceof MultiPoint) {
            return new PGgeometry((MultiPoint) objectValue);
        } else if (objectValue instanceof LineString) {
            return new PGgeometry((LineString) objectValue);
        } else if (objectValue instanceof MultiLineString) {
            return new PGgeometry((MultiLineString) objectValue);
        } else if (objectValue instanceof Polygon) {
            return new PGgeometry((Polygon) objectValue);
        } else if (objectValue instanceof MultiPolygon) {
            return new PGgeometry((MultiPolygon) objectValue);
        } else if (objectValue instanceof GeometryCollection) {
            return new PGgeometry((GeometryCollection) objectValue);
        } else {
            return new PGgeometry();
        }
    }

    @Override
    public void initialize(DatabaseMapping dm, Session sn) {
        dm.getField().setSqlType(java.sql.Types.OTHER);
    }

    @Override
    public boolean isMutable() {
        return false;
    }
}
