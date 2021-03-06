package org.nkjmlab.gis.datum.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.nkjmlab.gis.datum.Basis;
import org.nkjmlab.gis.datum.LatLon;
import org.nkjmlab.gis.datum.LatLon.Detum;
import org.nkjmlab.gis.datum.LatLon.Unit;
import org.nkjmlab.gis.datum.LatLonBox;
import org.nkjmlab.gis.datum.Tile;
import org.nkjmlab.gis.datum.jprect.util.LatLonUtils;

public class TileUtilsTest {

  @Before
  public void setUp() throws Exception {}

  @Test
  public void test() {
    testLatLonToTile(35.6235, 139.7804, 16, 58214, 25819);
    testTileToLatLon(35.6235, 139.7804, 16, 58214, 25819);
  }

  private void testLatLonToTile(double lat, double lon, int zoom, int x, int y) {
    Tile tile = LatLonUtils.toTile(new LatLon(lat, lon, Basis.of(Unit.DEGREE, Detum.WGS84)), zoom);

    System.out.println(tile);
    assertEquals(x, tile.getX());
    assertEquals(y, tile.getY());
  }

  private void testTileToLatLon(double lat, double lon, int zoom, int x, int y) {
    Tile tile = new Tile(x, y, zoom);
    Basis basis = Basis.of(Unit.DEGREE, Detum.WGS84);
    LatLonBox latLon = tile.toLatLonBox(basis);
    System.out.println(latLon);
    assertEquals(lat, latLon.getNorthEdgeLat(basis), 0.01);
    assertEquals(lon, latLon.getWestEdgeLon(basis), 0.01);
  }

}
