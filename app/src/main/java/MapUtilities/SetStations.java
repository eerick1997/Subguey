package MapUtilities;

import android.app.Activity;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import Objects.Exit;
import Objects.Service;
import Objects.Station;
import UIElements.ChangeStyle;
import UIElements.MyImages;

public class SetStations {

    //Constants
    private static final String TAG = "SetStations.java";
    //Variables
    private Activity activity;

    //Constructor
    public SetStations(Activity activity){
        Log.d(TAG, "SetStations() called with: activity = [" + activity + "]");
        this.activity = activity;
    }

    public void drawStations(GoogleMap googleMap) throws Exception{
        Log.d(TAG, "drawStations() called with: googleMap = [" + googleMap + "]");
        ChangeStyle changeStyle = new ChangeStyle(activity);
            ArrayList<Station> stations = new ArrayList<>();
            ArrayList<Service> services = null;
            ArrayList<Exit> exits = null;
            ArrayList<MLatLng> neighborhood = null;

            MLatLng position = null;

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();
            Station station = null;
            position = new MLatLng(19.416171, -99.074326);

            services.add(new Service("BICIESTACIONAMIENTO","Para estacionar bicicletas, bajo tu responsabilidad."));
            services.add(new Service("CIBERCENTRO","Lunes a viernes: 10:00 a 18:00 horas \n Sabado: 12:00 a 18:00 \n El servicio es gratuito, para su uso favor de presentar una identificación oficial, original y vigente."));
            services.add(new Service("MINISTERIO PÚBLICO","Se ubica sobre la calle Alberto Braniff y Manuel Lebrija s/n, colonia Ampliación Adolfo López Mateos. \n Horario de atención: las 24 horas los 365 días del año. \n Teléfonos: 5242-6569, 5242-6570."));
            services.add(new Service("MÓDULO DE INMUJERES", "En apoyo al “Programa Viaja Segura en el Transporte Público de la CDMX”, el Metro proporcionó el espacio para el Módulo ubicado en en el pasillo de transferencia de la Línea 1 a la  5. \n Horario de atención es de lunes a viernes de 8:00 a 20:00 horas."));
            services.add(new Service("VITRINA CULTURAL", "Cuatro vitrinas que se localizan sobre Línea 5, en el pasillo de correspondencia de Línea 1 con las Líneas A y 9, a un costado del Cibercentro."));
            services.add(new Service("MURAL", "Sobre Línea 5, se ubica el mural Alegoría de la Ciudad de México y el Sistema de Transporte Colectivo de José Luis Elias Jáuregui."));
            services.add(new Service("INSTALACIÓN PARA PERSONAS CON DISCAPACIDAD", "La estación cuenta con cuatro elevadores, dos sobre Línea 1 y dos sobre Línea A, los cuales se usan si cuenta con tarjeta Libre Acceso. Rampa de acceso, escaleras eléctricas sobre Línea 9, ranura guía y placas braille para invidentes sobre Línea 9 y Línea A."));
            services.add(new Service("MODULO DE SALUD", "De la Red Ángel, horario de atención: Lunes a viernes, de 7:00 a 20:00 horas y sábado de 9:00 a 14:00 horas."));

            exits.add(new Exit("Único", "Calle Alberto Braniffy y Miguel Lebrija, Colonia Aviación Civil."));

            neighborhood.add(new MLatLng(19.416765, -99.075056));
            neighborhood.add(new MLatLng(19.415672, -99.074193));
            neighborhood.add(new MLatLng(19.416201, -99.074034));
            neighborhood.add(new MLatLng(19.416411, -99.075536));
            neighborhood.add(new MLatLng(19.415784, -99.074858));

            station = new Station("Pantitlán", "metro 1", position, services, exits, neighborhood);
            stations.add(station);
            Marker marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.412009, -99.082623);

            services.add(new Service("CIBERCENTRO","Lunes a viernes: 08:00 a 20:00 horas \n Sabado: 12:30 a 18:00, \n El servicio es gratuito, para su uso favor de presentar una identificación oficial, original y vigente."));
            services.add(new Service("INSTALACIÓN PARA PERSONAS CON DISCAPACIDAD", "Un elevador, el cual se usa si cuenta con tarjeta Libre Acceso; rampas de acceso, escaleras eléctricas, ranura guía y placas braille para invidentes."));
            services.add(new Service("MÓDULO DE SALUD", "De la Red Ángel, horario de atención: Lunes a viernes, de 7:00 a 20:00 horas y sábado de 9:00 a 14:00 horas."));
            services.add(new Service("VITRINA CULTURAL", "Dos vitrinas, localizadas entre el andén y los torniquetes, dirección Observatorio."));

            exits.add(new Exit("Norte", "Calzada Ignacio Zaragoza, esquina Enrique Farman, Colonia 4 arboles"));
            exits.add(new Exit("Sur", "Calzada Ignacio Zaragoza, Calle 65, Colonia Puebla"));

            neighborhood.add(new MLatLng(19.412603, -99.083353));
            neighborhood.add(new MLatLng(19.412508, -99.082490));
            neighborhood.add(new MLatLng(19.412039, -99.082331));
            neighborhood.add(new MLatLng(19.412249, -99.083833));
            neighborhood.add(new MLatLng(19.411622, -99.083155));

            station = new Station("Zaragoza", "metro 1", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.416087, -99.090724);

            services.add(new Service("INSTALACIÓN PARA PERSONAS CON DISCAPACIDAD", "Escaleras eléctricas."));

            exits.add(new Exit("Norte", "Calzada Ignacio Zaragoza esquina Relaciones Exteriores, Colonia Federal"));
            exits.add(new Exit("Sur", "Calzada Ignacio Zaragoza esquina Gómez Farías, Colonia Gómez Farías"));

            neighborhood.add(new MLatLng(19.416681, -99.091454));
            neighborhood.add(new MLatLng(19.415588, -99.090591));
            neighborhood.add(new MLatLng(19.416117, -99.090432));
            neighborhood.add(new MLatLng(19.416327, -99.091934));
            neighborhood.add(new MLatLng(19.415700, -99.091256));

            station = new Station("Gómez Farías", "metro 1", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.419990, -99.096211);

            services.add(new Service("LÍNEA DE TROLEBUS","Línea G (Metro Blvd. Puerto Aéreo - Metro El Rosario) del trolebús dirección oriente-poniente y poniente-oriente, sobre la avenida Circuito Interior Boulevard Puerto Aéreo."));

            exits.add(new Exit("Norte", "Boulevard Puerto Aéreo, Colonia Aviación Civil"));
            exits.add(new Exit("Nororiente", "Boulevard Puerto Aéreo, Colonia Aviación Civil"));
            exits.add(new Exit("Norponiente", "Boulevard Puerto Aéreo, Colonia Moctezuma"));
            exits.add(new Exit("Sur", "Boulevard Puerto Aéreo, Colonia Valentín Gómez Farías"));
            exits.add(new Exit("Suroriente", "Calzada Ignacio Zaragoza, Colonia Valentín Gómez Farías"));
            exits.add(new Exit("Surponiente", "Boulevard Puerto Aéreo, Colonia Valentín Gómez Farías"));

            neighborhood.add(new MLatLng(19.420584, -99.096941));
            neighborhood.add(new MLatLng(19.419491, -99.096078));
            neighborhood.add(new MLatLng(19.420020, -99.095919));
            neighborhood.add(new MLatLng(19.420230, -99.097421));
            neighborhood.add(new MLatLng(19.419603, -99.096743));

            station = new Station("Boulevard Puerto Aéreo", "metro 1", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.422896, -99.102815);

            services.add(new Service("INSTALACIÓN PARA PERSONAS CON DISCAPACIDAD", "Rampa de acceso y escaleras eléctricas."));

            exits.add(new Exit("Norte", "Calzada Ignacio Zaragoza esquina Avenida de la Portilla, Colonia Jardín Balbuena"));
            exits.add(new Exit("Sur", "Calzada Ignacio Zaragoza esquina Calle 17, Colonia Moctezuma, primera sección"));
            neighborhood.add(new MLatLng(19.423490, -99.103545));
            neighborhood.add(new MLatLng(19.422397, -99.102682));
            neighborhood.add(new MLatLng(19.422926, -99.102523));
            neighborhood.add(new MLatLng(19.423136, -99.104025));
            neighborhood.add(new MLatLng(19.422509, -99.103347));

            station = new Station("Balbuena", "metro 1", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.427060, -99.109523);

            services.add(new Service("INSTALACIÓN PARA PERSONAS CON DISCAPACIDAD", "Escaleras eléctricas."));
            services.add(new Service("METROBUS","Línea 4 del Metrobús Moctezuma, ubicado en la  avenida Sidar y Rovirosa y Eje 3 Oriente Francisco del Paso y Troncoso."));

            exits.add(new Exit("Norte", "José Jasso esquina Calzada Ignacio Zaragoza, Colonia Moctezuma"));
            exits.add(new Exit("Sur", "primer Retorno de Ignacio Zaragoza esquina Calzada Ignacio Zaragoza, Colonia Balbuena"));

            neighborhood.add(new MLatLng(19.427654, -99.110253));
            neighborhood.add(new MLatLng(19.426561, -99.109390));
            neighborhood.add(new MLatLng(19.427090, -99.109231));
            neighborhood.add(new MLatLng(19.427300, -99.110733));
            neighborhood.add(new MLatLng(19.426673, -99.110055));

            station = new Station("Moctezuma", "metro 1", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.429868, -99.114416);

            services.add(new Service("CIBERCENTRO","Lunes a viernes de 8:00 a 20:00 horas. \n Sábados de 12:30 a 18:00 horas. \n El servicio es gratuito, para su uso favor de presentar una identificación oficial, original y vigente."));
            services.add(new Service("CENTRAL CAMIONERA","Acceso del lado norponiente a la Central de Autobuses de Oriente (también conocida como Tapo)."));
            services.add(new Service("INSTALACIÓN PARA PERSONAS CON DISCAPACIDAD","Dos elevadores sobre Línea B los cuales se usan si cuenta con tarjeta Libre Acceso; ranura guía  y placas braille para invidentes."));
            services.add(new Service("METROBUS","Línea 4 del Metrobús San Lázaro, ubicado en Eje 3 Oriente avenida. Ing. Eduardo Molina frente a la Terminal de Autobuses de Oriente (TAPO)."));
            services.add(new Service("MÓDULO DE SALUD","De la Red Ángel, el horario de atención: lunes a viernes de 07:00 a 20:00 horas y sábado de 09:00 a 14:00 horas."));
            services.add(new Service("VITRINA CULTURAL","Cinco vitrinas que se localizan, en el pasillo de correspondencia con las Líneas 1 y B."));

            exits.add(new Exit("Surponiente", "Avenida Zaragoza y Eje 3 Poniente, Colonia 10 de Mayo"));
            exits.add(new Exit("Suroriente", "Salida a Terminal de la Tapo, Colonia 10 de Mayo"));

            neighborhood.add(new MLatLng(19.430462, -99.115146));
            neighborhood.add(new MLatLng(19.429369, -99.114283));
            neighborhood.add(new MLatLng(19.429898, -99.114124));
            neighborhood.add(new MLatLng(19.430108, -99.115626));
            neighborhood.add(new MLatLng(19.429481, -99.114948));

            station = new Station("San Lázaro", "metro 1", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.429645, -99.120891);

            services.add(new Service("MÓDULO DE SALUD","De la Red Ángel, el horario de atención: lunes a viernes, de 7:00 a 20:00 horas y sábado de 9:00 a 14:00 horas."));
            services.add(new Service("VITRINA CULTURAL","Se localiza cerca del andén, dirección Pantitlán."));

            exits.add(new Exit("Norte", "Avenida Candelaria de los Patos, Colonia Candelaria de los Patos"));
            exits.add(new Exit("Sur", "Avenida Candelaria de los Patos, Colonia Candelaria de los Patos"));

            neighborhood.add(new MLatLng(19.430239, -99.121621));
            neighborhood.add(new MLatLng(19.429146, -99.120758));
            neighborhood.add(new MLatLng(19.429675, -99.120599));
            neighborhood.add(new MLatLng(19.429885, -99.122101));
            neighborhood.add(new MLatLng(19.429258, -99.121423));

            station = new Station("Candelaria", "metro 1", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();


            position = new MLatLng(19.425481, -99.125059);

            services.add(new Service("METROBUS","Línea 4 del Metrobús La Merced, ubicado en Eje 1 Oriente Anillo de Circunvalación y calle San Pablo; y la estación Cecilio Robelo, ubicada en Eje 2 Oriente Congreso de la Unión y calle Cecilio Robelo."));

            exits.add(new Exit("Poniente", "Avenida Anillo de Circunvalación y Plaza Carrizal, Colonia Merced"));
            exits.add(new Exit("Oriente", "Al interior del Mercado Merced, Colonia Merced Balbuena"));

            neighborhood.add(new MLatLng(19.426075, -99.125789));
            neighborhood.add(new MLatLng(19.424982, -99.124926));
            neighborhood.add(new MLatLng(19.425511, -99.124767));
            neighborhood.add(new MLatLng(19.425721, -99.126269));
            neighborhood.add(new MLatLng(19.425094, -99.125291));

            station = new Station("Merced", "metro 1", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.425932, -99.133019);

            services.add(new Service("CIBERCENTRO","Horario de servicio es de lunes a viernes de 8:00 a 20:00 horas y sábados de 12:30 a 18:00 horas. \n El servicio es gratuito, para su uso favor de presentar una identificación oficial, original y vigente."));
            services.add(new Service("MÓDULO DE INMUJERES","Módulos cerrados para reubicación: Pino Suárez y Guerrero, para continuar con las tareas en la estación Pino Suárez, se llevará durante el primer semestre, jornadas de 12:30 a 15:30 horas los días martes y jueves."));
            services.add(new Service("METROBUS","Línea 4 del Metrobús Pino Suárez, ubicado en  avenida José María Pino Suárez y calle San Jerónimo."));
            services.add(new Service("INSTALACIÓN PARA PERSONAS CON DISCAPACIDAD","Tres elevadores, dos sobre Línea 1 y uno sobre Línea 2, los cuales se usan si cuenta con tarjeta Libre Acceso; escaleras eléctricas y línea de vida foto luminiscente."));
            services.add(new Service("MÓDULO DE ORIENTACIÓN E INFORMACIÓN","Al inicio del pasillo de correspondencia de Línea 1 a Línea 2, dirección Pantitlán. \n El horario de atención es de lunes a viernes de 7:30 a 14:30 horas."));
            services.add(new Service("VITRINA CULTURAL","Veintiún Vitrinas; dos se localizan cerca de los torniquetes del pasillo de correspondencia de las Líneas 1 y 2; otras catorce se localizan en el pasillo que da a la plaza comercial Pino Suárez y en el pasillo de correspondencia con las Líneas 1 y 2, dirección Cuatro Caminos y  cinco  vitrinas ubicadas entre el andén y los torniquetes, dirección Pantitlán."));

            exits.add(new Exit("Norte", "José María Izazaga y Pino Suárez, Colonia Centro"));
            exits.add(new Exit("Poniente", "José María Izazaga y Pino Suárez, Colonia Centro"));

            neighborhood.add(new MLatLng(19.426526, -99.133749));
            neighborhood.add(new MLatLng(19.425433, -99.132886));
            neighborhood.add(new MLatLng(19.425962, -99.132727));
            neighborhood.add(new MLatLng(19.426172, -99.134229));
            neighborhood.add(new MLatLng(19.425545, -99.133531));

            station = new Station("Pino Suárez", "metro 1", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.426094, -99.137939);

            services.add(new Service("INSTALACIÓN PARA  PERSONAS CON DISCAPACIDAD","Rampa de acceso, escaleras eléctricas, ranura guía y placas braille para invidentes."));
            services.add(new Service("MURAL","ENCUENTRO CON LA LUZ, Autor: Luis López Loza"));

            exits.add(new Exit("Norte", "Avenida José María Izazaga casi esquina Isabel la Católica, Colonia Centro"));
            exits.add(new Exit("Sur", "Avenida José María Izazaga casi esquina Isabel la Católica, Colonia Centro"));

            neighborhood.add(new MLatLng(19.426688, -99.138669));
            neighborhood.add(new MLatLng(19.425595, -99.137806));
            neighborhood.add(new MLatLng(19.426124, -99.137647));
            neighborhood.add(new MLatLng(19.426334, -99.139149));
            neighborhood.add(new MLatLng(19.425707, -99.138471));

            station = new Station("Isabel la Católica", "metro 1", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.426888, -99.143410);

            services.add(new Service("LÍNEA DE TROLEBÚS","Línea A (corredor cero emisiones Eje Central) dirección norte-sur y dirección sur-norte, sobre el Eje Central Lázaro Cárdenas."));
            services.add(new Service("INSTALACIÓN PARA  PERSONAS CON DISCAPACIDAD","Cuatro rampas de acceso y escaleras eléctricas."));
            services.add(new Service("VITRINA CULTURAL","En el pasillo de correspondencia de las Líneas 1 y 8."));

            exits.add(new Exit("Norte", "Avenida Arcos de Belén Norte, Colonia Centro"));
            exits.add(new Exit("Sur", "Avenida Arcos de Belén Sur, Colonia Centro"));

            neighborhood.add(new MLatLng(19.427482, -99.144140));
            neighborhood.add(new MLatLng(19.426389, -99.143277));
            neighborhood.add(new MLatLng(19.426918, -99.143118));
            neighborhood.add(new MLatLng(19.427128, -99.144620));
            neighborhood.add(new MLatLng(19.426501, -99.143942));

            station = new Station("Salto del Agua", "metro 1", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.427212, -99.148946);

            services.add(new Service("MÓDULO DE INMUJERES","En apoyo al “Programa Viaja Segura en el Transporte Público de la CDMX”, el Metro proporcionó el espacio para el Módulo ubicado en el pasillo de correspondencia entre Línea 3 y 1. \n El horario de atención es de lunes a viernes de 8:00 a 20:00 horas."));
            services.add(new Service("INSTALACIÓN PARA  PERSONAS CON DISCAPACIDAD","Tres elevadores, una sobre Línea 1 y dos sobre Línea 3, los cuales se usan si cuenta con tarjeta Libre Acceso y escaleras eléctricas."));
            services.add(new Service("METROBUS","Línea 3 del Metrobús Balderas, ubicado  en avenida Balderas y calle Tolsá."));
            services.add(new Service("MÓDULO DE ORIENTACIÓN E INFORMACIÓN","Entre el andén dirección Observatorio y el pasillo a Línea 3. \n El horario de atención es de lunes a viernes de 9:00 a 16:00 horas."));

            exits.add(new Exit("Norte", "Calle Tolsá y Balderas, Colonia Centroo"));
            exits.add(new Exit("Sur", "Avenida Niños Héroes y Avenida Chapultepec, Colonia Doctores"));

            neighborhood.add(new MLatLng(19.427806, -99.149676));
            neighborhood.add(new MLatLng(19.426713, -99.148813));
            neighborhood.add(new MLatLng(19.427242, -99.148654));
            neighborhood.add(new MLatLng(19.427452, -99.150156));
            neighborhood.add(new MLatLng(19.426825, -99.149478));

            station = new Station("Balderas", "metro 1", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.425664, -99.154552);

            services.add(new Service("INSTALACIÓN PARA  PERSONAS CON DISCAPACIDAD","Rampas de acceso, escaleras eléctricas y Línea de vida fotoluminiscente."));
            services.add(new Service("METROBUS","Línea 3 del Metrobús Cuauhtémoc, ubicado en Eje 1 Poniente avenida Cuauhtémoc y avenida Chapultepec."));

            exits.add(new Exit("Norte", "Avenida Chapultepec, Colonia Juárez"));
            exits.add(new Exit("Sur", "Avenida Chapultepec, Colonia Roma"));

            neighborhood.add(new MLatLng(19.426258, -99.153822));
            neighborhood.add(new MLatLng(19.425165, -99.154419));
            neighborhood.add(new MLatLng(19.425694, -99.154260));
            neighborhood.add(new MLatLng(19.425904, -99.155762));
            neighborhood.add(new MLatLng(19.425277, -99.155084));

            station = new Station("Cuauhtémoc", "metro 1", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.423731, -99.163318);

            services.add(new Service("BICIESTACIONAMIENTO","Para estacionar bicicletas, bajo tu responsabilidad."));
            services.add(new Service("MURAL","EL METRO DE LÓNDRES Y EL METRO DE PARÍS, Autor: Rafael Cauduro"));
            services.add(new Service("METROBUS","La Línea 1 del Metrobús Insurgentes, ubicado en avenida Chapultepec y calles Jalapa y Liverpool."));
            services.add(new Service("MÓDULO DE ORIENTACIÓN E INFORMACIÓN","Antes de los torniquetes, dirección Observatorio. \n El horario de atención es de lunes a viernes de 8:00 a 15:00 horas"));

            exits.add(new Exit("Surponiente", "Oaxaca Oriente, Colonia Roma"));
            exits.add(new Exit("Suroriente", "Insurgentes, Colonia Roma Norte"));
            exits.add(new Exit("Sur", "Jalapa, Colonia Roma Norte"));
            exits.add(new Exit("Nororiente", "Avenida Chapultepec, Colonia Juárez"));
            exits.add(new Exit("Norte", "Génova, Colonia Juárez"));
            exits.add(new Exit("Norponiente", "Avenida Chapultepec, Colonia Juárez"));

            neighborhood.add(new MLatLng(19.424325, -99.164048));
            neighborhood.add(new MLatLng(19.423232, -99.163185));
            neighborhood.add(new MLatLng(19.423761, -99.163026));
            neighborhood.add(new MLatLng(19.423971, -99.164528));
            neighborhood.add(new MLatLng(19.423344, -99.163850));

            station = new Station("Insurgentes", "metro 1", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.422127, -99.171187);

            services.add(new Service("BICIESTACIONAMIENTO","Para estacionar bicicletas, bajo tu responsabilidad."));
            services.add(new Service("INSTALACIÓN PARA PERSONAS CON DISCAPACIDAD","Rampas de acceso y escaleras eléctricas."));

            exits.add(new Exit("Sur", "Avenida Chapultepec Sur, Colonia Roma Norte"));
            exits.add(new Exit("Norponiente", "Avenida Chapultepec, Colonia Juárez"));
            exits.add(new Exit("Nororiente", "Avenida Chapultepec, Colonia Juárez"));

            neighborhood.add(new MLatLng(19.422721, -99.171917));
            neighborhood.add(new MLatLng(19.421628, -99.171054));
            neighborhood.add(new MLatLng(19.422157, -99.170895));
            neighborhood.add(new MLatLng(19.422367, -99.172397));
            neighborhood.add(new MLatLng(19.421740, -99.171719));

            station = new Station("Sevilla", "metro 1", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.420149, -99.177029);

            services.add(new Service("BICIESTACIONAMIENTO","Para estacionar bicicletas, bajo tu responsabilidad."));
            services.add(new Service("CIBERCENTRO","Lunes a viernes de 8:00 a 20:00 horas y sábados de 12:30 a 18:00 horas. \n El servicio es gratuito, para su uso favor de presentar una identificación oficial, original y vigente."));
            services.add(new Service("LÍNEA DE TROLEBUS","Línea I (Metro El Rosario - Metro Chapultepec) dirección norte-sur y sur-norte \n Línea S (Corredor cero emisiones Eje 2 - 2A Sur) dirección oriente-poniente y poniente-oriente, ambas en el paradero de avenida Chapultepec, colonia Roma Norte."));
            services.add(new Service("MÓDULO DE ORIENTACIÓN E INFORMACIÓN","En el vestíbulo principal, entre los torniquetes de salida. \n Horario de atención es de lunes a viernes de 9:00 a 15:00 horas."));

            exits.add(new Exit("Oriente", "Calle Tampico y Avenida Chapultepec, Colonia Roma Norte"));
            exits.add(new Exit("Nororiente", "Avenida José Vasconcelos Circuito Interior y Paradero, Colonia Juárez"));
            exits.add(new Exit("Poniente", "Avenida Circuito. Interior, Colonia San Miguel Chapultepec"));
            exits.add(new Exit("Norponiente", "Avenida Circuito Interior, Colonia Juárez"));
            exits.add(new Exit("Norte", "Avenida Circuito Interior y 1ª sección del Bosque de Chapultepec"));
            exits.add(new Exit("Sur ", "Avenida José Vasconcelos, Colonia Condensa"));

            neighborhood.add(new MLatLng(19.420743, -99.177759));
            neighborhood.add(new MLatLng(19.419650, -99.176896));
            neighborhood.add(new MLatLng(19.420179, -99.176737));
            neighborhood.add(new MLatLng(19.420389, -99.178239));
            neighborhood.add(new MLatLng(19.419762, -99.177561));

            station = new Station("Chapultepec", "metro 1", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.412757, -99.182163);

            services.add(new Service("INSTALACIÓN PARA PERSONAS CON DISCAPACIDAD","Rampas de acceso."));

            exits.add(new Exit("Nororiente", "Avenida Pedro Antonio de los Santos, Colonia San Miguel Chapultepec"));
            exits.add(new Exit("Oriente", "Avenida Circuito Interior, Colonia San Miguel Chapultepec"));
            exits.add(new Exit("Surponiente", "Avenida Pedro Antonio de los Santos, Colonia San Miguel Chapultepec"));

            neighborhood.add(new MLatLng(19.413351, -99.182893));
            neighborhood.add(new MLatLng(19.412258, -99.182030));
            neighborhood.add(new MLatLng(19.412787, -99.181871));
            neighborhood.add(new MLatLng(19.412997, -99.183373));
            neighborhood.add(new MLatLng(19.412370, -99.182695));

            station = new Station("Juanacatlán", "metro 1", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.402426, -99.188386);

            services.add(new Service("CIBERCENTRO","Lunes a viernes de 8:00 a 20:00 horas y sábados de 12:30 a 18:00 horas. \n El servicio es gratuito, para su uso favor de presentar una identificación oficial, original y vigente."));
            services.add(new Service("INSTALACIÓN PARA PERSONAS CON DISCAPACIDAD ","Escaleras eléctricas sobre Línea 9, ranura guía y placas braille para invidentes sobre Línea 9."));
            services.add(new Service("METROBÚS","Línea 2 del Metrobús Tacubaya, ubicado en las avenidas Parque Lira y Jalisco."));
            services.add(new Service("MÓDULO DE SALUD","De la Red Ángel, el horario de atención es: lunes a viernes de 07:00 a 20:00 horas y sábado de 09:00 a 14:00 horas."));
            services.add(new Service("MURAL","DEL CÓDICE AL MURAL, Autor: Guillermo Ceniceros"));
            services.add(new Service("VITRINA CULTURAL","Cuatro vitrinas que se localizan cerca de las escaleras de la salida hacia  Av. Jalisco; del lado del andén con dirección a Observatorio."));

            exits.add(new Exit("Nororiente", "Avenida Parque Lira, Colonia Tacubaya"));
            exits.add(new Exit("Norponiente", "E. Castellanos (Mercado Cartagena), Colonia Tacubaya"));
            exits.add(new Exit("Surponiente", "Avenida Jalisco y Rufina, Colonia Tacubaya"));
            exits.add(new Exit("Suroriente", " Parque Lira, Colonia Tacubaya"));

            neighborhood.add(new MLatLng(19.403020, -99.189116));
            neighborhood.add(new MLatLng(19.401927, -99.188253));
            neighborhood.add(new MLatLng(19.402456, -99.188094));
            neighborhood.add(new MLatLng(19.402666, -99.189596));
            neighborhood.add(new MLatLng(19.402039, -99.188918));

            station = new Station("Tacubaya", "metro 1", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.398241, -99.200418);

            services.add(new Service("BICIESTACIONAMIENTO","Sobre la calle Escuadrón 201 s/n, colonia Real del Monte, frente a la terminal de Autobuses Observatorio-Poniente. \n El horario de atención es de lunes a viernes de 9:00 a 19:00 horas. \n Teléfonos 5242-6144, 5242-6145."));
            services.add(new Service("CENTRAL CAMIONERA","Por la salida dirección Pantitlán, cruzando la calle se encuentra la Central de autobuses Poniente (también conocida como Central Observatorio)."));
            services.add(new Service("INSTALACIÓN PARA PERSONAS CON DISCAPACIDAD","Dos elevadores, los cuales se usan si cuenta con tarjeta Libre Acceso y rampas de acceso."));
            services.add(new Service("MINISTERIO PUBLICO","Sobre la calle Escuadrón 201 s/n, colonia Real del Monte, frente a la terminal de Autobuses Observatorio-Poniente. El horario de atención es de lunes a viernes de 9:00 a 19:00 horas, teléfonos 5242-6144, 5242-6145."));
            exits.add(new Exit("Nororiente", "Avenida Minas de Arena, Colonia Pino Suárez"));
            exits.add(new Exit("Norponiente", "Avenida Minas de Arena, Colonia Pino Suárez "));
            exits.add(new Exit("Surponiente", "Real del Monte, Colonia Pino Suárez "));
            exits.add(new Exit("Suroriente", "Real del Monte, Colonia Pino Suárez "));

            neighborhood.add(new MLatLng(19.398835, -99.201148));
            neighborhood.add(new MLatLng(19.397742, -99.200285));
            neighborhood.add(new MLatLng(19.398271, -99.200126));
            neighborhood.add(new MLatLng(19.398481, -99.201628));
            neighborhood.add(new MLatLng(19.397854, -99.200950));

            station = new Station("Observatorio", "metro 1", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.460361, -99.215742);

            services.add(new Service("INSTALACIÓN PARA PERSONAS CON DISCAPACIDAD","Dos elevadores, los cuales se usan si cuenta con tarjeta Libre Acceso."));

            exits.add(new Exit("Sur", "Avenida Ingenieros Militares, Colonia Argentina Poniente"));
            exits.add(new Exit("Norte", "Avenida 16 de Septiembre, Colonia Transmisiones"));

            neighborhood.add(new MLatLng(19.460955, -99.216472));
            neighborhood.add(new MLatLng(19.459862, -99.215609));
            neighborhood.add(new MLatLng(19.460391, -99.215450));
            neighborhood.add(new MLatLng(19.460601, -99.216952));
            neighborhood.add(new MLatLng(19.459974, -99.216274));

            station = new Station("Cuatro Caminos", "metro 2", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.458197, -99.202953);

            exits.add(new Exit("Surponiente", "Calzada San Bartolo Naucalpan esquina calle Lago Fontana, Colonia Argentina"));
            exits.add(new Exit("Suroriente", "Calzada San Bartolo Naucalpan esquina calle Lago Buenos Aires, Colonia Argentina"));
            exits.add(new Exit("Norte", "Calzada San Bartolo Naucalpan entrada a panteones de Monte Sinaí, Alemán y Español, Colonia Argentina"));

            neighborhood.add(new MLatLng(19.458791, -99.203683));
            neighborhood.add(new MLatLng(19.457698, -99.202820));
            neighborhood.add(new MLatLng(19.458227, -99.202661));
            neighborhood.add(new MLatLng(19.458437, -99.204163));
            neighborhood.add(new MLatLng(19.457810, -99.203485));

            station = new Station("Panteones", "metro 2", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.458935, -99.188298);

            services.add(new Service("BICIESTACIONAMIENTO","Para estacionar bicicletas, bajo tu responsabilidad."));
            services.add(new Service("INSTALACIÓN PARA PERSONAS CON DISCAPACIDAD","Escaleras eléctricas."));
            services.add(new Service("MÓDULO DE SALUD","De la Red Ángel. El horario de atención es: lunes a viernes, de 07:00 a 20:00 horas y sábado de 09:00 a 14:00 horas."));
            services.add(new Service("VITRINA CULTURAL","Dos vitrinas, ubicadas en el pasillo de correspondencia con las Líneas 2 y 7."));

            exits.add(new Exit("Surponiente", "Plaza de locales comerciales y paraderos, Colonia Tacuba"));
            exits.add(new Exit("Suroriente", "Parroquia de San Miguel Arcángel, Colonia Tacuba"));
            exits.add(new Exit("Norte", "Calzada México Tacuba s/n, Colonia Tacuba"));

            neighborhood.add(new MLatLng(19.459529, -99.189028));
            neighborhood.add(new MLatLng(19.458436, -99.188165));
            neighborhood.add(new MLatLng(19.458965, -99.188006));
            neighborhood.add(new MLatLng(19.453175, -99.189508));
            neighborhood.add(new MLatLng(19.458548, -99.188830));

            station = new Station("Tacuba", "metro 2", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.456669, -99.181581);

            services.add(new Service("LÍNEA DE TROLEBÚS","Línea I (Metro El Rosario - Metro Chapultepec) dirección norte-sur y sur-norte, sobre la Avenida Cuitláhuac."));

            exits.add(new Exit("Sur", "Calzada México Tacuba s/n casi esquina con Avenida Cuitláhuac, Colonia Popotla"));
            exits.add(new Exit("Norte", "Calzada México Tacuba s/n, casi esquina con Avenida Cuitláhuac, Colonia San Álvaro"));

            neighborhood.add(new MLatLng(19.457263, -99.182311));
            neighborhood.add(new MLatLng(19.456170, -99.181448));
            neighborhood.add(new MLatLng(19.456699, -99.181289));
            neighborhood.add(new MLatLng(19.456909, -99.182791));
            neighborhood.add(new MLatLng(19.456282, -99.182113));

            station = new Station("Cuitláhuac", "metro 2", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.452936, -99.175369);

            exits.add(new Exit("Sur", "Calzada México-Tacuba esquina con calle Colegio Militar, Colonia Popotla"));
            exits.add(new Exit("Norte", "Calzada México-Tacuba esquina con callejón de la Zanja, Colonia Popotla"));

            neighborhood.add(new MLatLng(19.453530, -99.176099));
            neighborhood.add(new MLatLng(19.452437, -99.175236));
            neighborhood.add(new MLatLng(19.452966, -99.175077));
            neighborhood.add(new MLatLng(19.453176, -99.176579));
            neighborhood.add(new MLatLng(19.452549, -99.175901));

            station = new Station("Popotla", "metro 2", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.449072, -99.172129);
            exits.add(new Exit("Surponiente", "Calzada Tacuba esquina con Carrillo Puerto, Colonia Anáhuac"));
            exits.add(new Exit("Suroriente", "Calzada Tacuba esquina con Carrillo Puerto, Colonia Anáhuac"));
            exits.add(new Exit("Norte", "Calzada México Tacuba, Colonia Un Hogar para Nosotros"));

            neighborhood.add(new MLatLng(19.449666, -99.172859));
            neighborhood.add(new MLatLng(19.448573, -99.171996));
            neighborhood.add(new MLatLng(19.449102, -99.171837));
            neighborhood.add(new MLatLng(19.449312, -99.173339));
            neighborhood.add(new MLatLng(19.448685, -99.173661));

            station = new Station("Colegio Militar", "metro 2", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.444812, -99.167291);

            exits.add(new Exit("Nororiente ", "Calzada México-Tacuba esquina con Avenida de los Maestros, Colonia Tlaxpana "));
            exits.add(new Exit("Norponiente ", "Calzada México-Tacuba esquina con Avenida de los Maestros, Colonia Tlaxpana "));
            exits.add(new Exit("Sur ", "Calzada México-Tacuba esquina calle Tláloc, Colonia Un Hogar para Nosotros "));

            neighborhood.add(new MLatLng(19.445406, -99.168021));
            neighborhood.add(new MLatLng(19.444313, -99.167158));
            neighborhood.add(new MLatLng(19.444842, -99.166999));
            neighborhood.add(new MLatLng(19.445052, -99.168501));
            neighborhood.add(new MLatLng(19.444425, -99.167823));

            station = new Station("Normal", "metro 2", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.441990, -99.160703);

            exits.add(new Exit("Norte", "Ribera de San Cosme esquina con 1ª calle de Naranjo, Colonia Santa María La Ribera"));
            exits.add(new Exit("Sur ", "Ribera de San Cosme esquina con Rosas Moreno, Colonia San Rafael"));

            neighborhood.add(new MLatLng(19.442584, -99.161433));
            neighborhood.add(new MLatLng(19.441491, -99.160570));
            neighborhood.add(new MLatLng(19.442020, -99.160411));
            neighborhood.add(new MLatLng(19.442230, -99.161913));
            neighborhood.add(new MLatLng(19.441603, -99.161235));

            station = new Station("San Cosme", "metro 2", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.438864, -99.154255);

            services.add(new Service("INSTALACIÓN PARA PERSONAS CON DISCAPACIDAD","Rampas de acceso, ranura guía, placas braille para invidentes y escaleras eléctricas."));
            services.add(new Service("METROBÚS","Línea 1 del Metrobús Revolución, ubicado en Puente de Alvarado y Avenida Insurgentes Norte. \n Línea 4 del Metrobús Puente de Alvarado, ubicado en la avenida Puente de Alvarado y Calle Ponciano Arriaga."));

            exits.add(new Exit("Nororiente", "Calle Puente de Alvarado esquina con B. de Sahaún, Colonia Buenavista"));
            exits.add(new Exit("Norponiente", "Calle Puente de Alvarado, Colonia Buenavista"));
            exits.add(new Exit("Sur ", "Calle Puente de Alvarado, Colonia Tabacalera"));

            neighborhood.add(new MLatLng(19.439458, -99.154985));
            neighborhood.add(new MLatLng(19.438365, -99.154122));
            neighborhood.add(new MLatLng(19.438894, -99.153963));
            neighborhood.add(new MLatLng(19.439104, -99.155465));
            neighborhood.add(new MLatLng(19.438477, -99.154787));

            station = new Station("Revolución", "metro 2", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.437710, -99.147335);

            services.add(new Service("CIBERCENTRO","Lunes a viernes de 8:00 a 20:00 horas. \n Sábados de 12:30 a 18:00 horas. \n El servicio es gratuito, para su uso favor de presentar una identificación oficial, original y vigente."));
            services.add(new Service("LÍNEA DE TROLEBÚS","Línea LL (San Felipe de Jesús - Metro Hidalgo) dirección oriente-poniente y poniente-oriente, sobre Paseo de la Reforma."));
            services.add(new Service("MINISTERIO PÚBLICO","Al interior de la estación a un costado de la salida a la Av. Puente de Alvarado. \n El horario de atención es de lunes a viernes de 9:00 a 19:00 horas, teléfono 5200-9412"));
            services.add(new Service("MÓDULO DE INMUJERES","En apoyo al “Programa Viaja Segura en el Transporte Público de la CDMX”, el Metro proporcionó el espacio para el Módulo ubicado al interior del Ministerio Público en las escaleras de salida por Eje 1 Sur Guerrero. \n Horario de atención es de lunes a viernes de de 09:00 a 19:00 horas."));
            services.add(new Service("INSTALACIÓN PARA PERSONAS CON DISCAPACIDAD","Escaleras eléctricas."));
            services.add(new Service("METROBÚS","Línea 3 del Metrobús Hidalgo, ubicado  en las avenidas Hidalgo y Balderas."));
            services.add(new Service("MÓDULO DE ORIENTACIÓN E INFORMACIÓN","Sobre el pasillo de correspondencia de Línea 2. \n El horario de atención es de lunes a viernes de 8:30 a 16:00 horas."));
            services.add(new Service("VITRINA CULTURAL","Dos vitrinas, ubicadas cerca del Cibercentro de la estación, con dirección Indios Verdes."));

            exits.add(new Exit("Norte", "Avenida Hidalgo esquina Héroes, Colonia Tabacalera"));
            exits.add(new Exit("Suroriente", "Calle Balderas esquina  Paseo a la Reforma, Colonia Tabacalera"));
            exits.add(new Exit("Sur ", "Reforma esquina  Dr. Basilio Vadillo, Colonia Tabacalera"));
            exits.add(new Exit("Sur ", "Eje de Guerrero, Colonia Guerrero"));
            exits.add(new Exit("Norte", "Eje de Guerrero, Colonia Guerrero"));

            neighborhood.add(new MLatLng(19.438304, -99.148065));
            neighborhood.add(new MLatLng(19.437211, -99.147202));
            neighborhood.add(new MLatLng(19.437740, -99.147043));
            neighborhood.add(new MLatLng(19.437950, -99.148545));
            neighborhood.add(new MLatLng(19.437323, -99.147867));

            station = new Station("Hidalgo", "metro 2", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.435930, -99.141992);

            services.add(new Service("CIBERCENTRO","Lunes a viernes de 8:00 a 20:00 horas y sábados de 12:30 a 18:00 horas. \n El servicio es gratuito, para su uso favor de presentar una identificación oficial, original y vigente."));
            services.add(new Service("LÍNEA DE TROLEBÚS","Línea A (corredor cero emisiones Eje Central) dirección norte-sur y sur-norte, sobre el Eje Central Lázaro Cárdenas."));
            services.add(new Service("INSTALACIÓN PARA PERSONAS CON DISCAPACIDAD ","Rampa de acceso y línea de vida fotoluminiscente."));
            services.add(new Service("METROBÚS","Línea 4 del Metrobús Bellas Artes, ubicado en Avenida Hidalgo y Eje Central Lázaro Cárdenas."));
            services.add(new Service("VITRINA CULTURAL","En el pasillo de correspondencia con las Líneas 2 y 8."));

            exits.add(new Exit("Sur", "Avenida Hidalgo y Parque de la Alameda, Colonia Centro"));
            exits.add(new Exit("Suroriente", "Avenida Hidalgo y Parque de la Alameda, Colonia Centro"));
            exits.add(new Exit("Suroriente", "Avenida Hidalgo y Palacio de Bellas Artes, Colonia Centro"));
            exits.add(new Exit("Norte", "Avenida Hidalgo esquina Héroes (Iglesia San Hipólito), Colonia Tabacalera"));

            neighborhood.add(new MLatLng(19.436524, -99.142722));
            neighborhood.add(new MLatLng(19.435431, -99.141859));
            neighborhood.add(new MLatLng(19.435960, -99.141700));
            neighborhood.add(new MLatLng(19.436170, -99.143202));
            neighborhood.add(new MLatLng(19.435543, -99.142524));

            station = new Station("Bellas Artes", "metro 2", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.435393, -99.136896);

            exits.add(new Exit("Norponiente", " Calle Tacuba, Colonia Centro"));
            exits.add(new Exit("Surponiente", " Calle Tacuba, Colonia Centro"));
            exits.add(new Exit("Suroriente", "Calle Tacuba esquina calle Motolinia, Colonia Centro"));
            exits.add(new Exit("Nororiente", "Calle Tacuba, Colonia Centro"));

            neighborhood.add(new MLatLng(19.435987, -99.137626));
            neighborhood.add(new MLatLng(19.434894, -99.136763));
            neighborhood.add(new MLatLng(19.435423, -99.136604));
            neighborhood.add(new MLatLng(19.435633, -99.138160));
            neighborhood.add(new MLatLng(19.435006, -99.137428));

            station = new Station("Allende", "metro 2", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.433097, -99.132615);

            services.add(new Service("CIBERCENTRO","Lunes a viernes de 8:00 a 20:00 horas y sábados de 12:30 a 18:00 horas. \n El servicio es gratuito, para su uso favor de presentar una identificación oficial, original y vigente."));
            services.add(new Service("INSTALACIÓN PARA PERSONAS CON DISCAPACIDAD","Un elevador, el cual se usa si cuenta con tarjeta Libre Acceso."));
            services.add(new Service("MÓDULO DE ORIENTACIÓN E INFORMACIÓN","En el pasillo central a un costado de las maquetas, de la Estación. \n El horario de atención es de lunes a viernes de 8:00 a 15:00 horas."));
            services.add(new Service("MURAL","CENEFAS CONMEMORATIVAS BICENTENARIO 2010 de los Arq. Juan Carlos Garcés y Cristóbal Flores."));
            services.add(new Service("VITRINA CULTURAL","Dos vitrinas, que se localizan por la salida hacia el Palacio Nacional, dirección Cuatro Caminos."));

            exits.add(new Exit("Norponiente", "Catedral Metropolitana de la Ciudad de México"));
            exits.add(new Exit("Surponiente", "Avenida Pino Suárez casi esquina Venustiano Carranza, Colonia Centro"));
            exits.add(new Exit("Suroriente", "Avenida Pino Suárez esquina Corregidora, Colonia Centro"));
            exits.add(new Exit("Nororiente", "Palacio casi esquina calle Moneda, Colonia Centro"));
            exits.add(new Exit("Oriente", " Palacio Nacional Colonia, Centro"));
            exits.add(new Exit("Poniente", " Plaza de la Constitución, Colonia Centro"));

                    neighborhood.add(new MLatLng(19.433691, -99.133345));
            neighborhood.add(new MLatLng(19.432598, -99.132482));
            neighborhood.add(new MLatLng(19.433127, -99.132323));
            neighborhood.add(new MLatLng(19.433337, -99.133825));
            neighborhood.add(new MLatLng(19.432710, -99.133147));

            station = new Station("Zócalo", "metro 2", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.425923, -99.133023);

            services.add(new Service("CIBERCENTRO","Horario de servicio es de lunes a viernes de 8:00 a 20:00 horas y sábados de 12:30 a 18:00 horas. \n El servicio es gratuito, para su uso favor de presentar una identificación oficial, original y vigente."));
            services.add(new Service("MÓDULO DE INMUJERES","Módulos cerrados para reubicación: Pino Suárez y Guerrero, para continuar con las tareas en la estación Pino Suárez, se llevará durante el primer semestre, jornadas de 12:30 a 15:30 horas los días martes y jueves."));
            services.add(new Service("METROBUS","Línea 4 del Metrobús Pino Suárez, ubicado en  avenida José María Pino Suárez y calle San Jerónimo."));
            services.add(new Service("INSTALACIÓN PARA PERSONAS CON DISCAPACIDAD","Tres elevadores, dos sobre Línea 1 y uno sobre Línea 2, los cuales se usan si cuenta con tarjeta Libre Acceso; escaleras eléctricas y línea de vida foto luminiscente."));
            services.add(new Service("MÓDULO DE ORIENTACIÓN E INFORMACIÓN","Al inicio del pasillo de correspondencia de Línea 1 a Línea 2, dirección Pantitlán. \n El horario de atención es de lunes a viernes de 7:30 a 14:30 horas."));
            services.add(new Service("VITRINA CULTURAL","Veintiún Vitrinas; dos se localizan cerca de los torniquetes del pasillo de correspondencia de las Líneas 1 y 2; otras catorce se localizan en el pasillo que da a la plaza comercial Pino Suárez y en el pasillo de correspondencia con las Líneas 1 y 2, dirección Cuatro Caminos y  cinco  vitrinas ubicadas entre el andén y los torniquetes, dirección Pantitlán."));

            exits.add(new Exit("Sur", "Calle cerrada San Lucas y Avenida Fray Servando Teresa de Mier, Colonia Centro"));
            exits.add(new Exit("Norte", "Avenida José María Izazaga y Calzada San Antonio Abad, Colonia Centro"));

            neighborhood.add(new MLatLng(19.426526, -99.133749));
            neighborhood.add(new MLatLng(19.425433, -99.132886));
            neighborhood.add(new MLatLng(19.425962, -99.132727));
            neighborhood.add(new MLatLng(19.426172, -99.134229));
            neighborhood.add(new MLatLng(19.425545, -99.133531));

            station = new Station("Pino Suárez", "metro 2", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.415886, -99.134310);

            services.add(new Service("INSTALACIÓN PARA PERSONAS CON DISCAPACIDAD","Rampa de acceso."));

            exits.add(new Exit("Oriente", "Entre calle Gutiérrez Nájera, calle Alfredo Chavero y Avenida San Antonio Abad, Colonia Tránsito"));
            exits.add(new Exit("Poniente", "Entre calle M. Flores, calle José Joaquín Arriaga y Avenida San Antonio Aba, Colonia Obrera"));

            neighborhood.add(new MLatLng(19.416480, -99.135050));
            neighborhood.add(new MLatLng(19.415387, -99.134177));
            neighborhood.add(new MLatLng(19.415916, -99.134018));
            neighborhood.add(new MLatLng(19.416126, -99.135520));
            neighborhood.add(new MLatLng(19.415499, -99.134842));

            station = new Station("San Antonio Abad", "metro 2", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.409015, -99.135619);

            services.add(new Service("CIBERCENTRO","Lunes a viernes de 10:00 a 18:00 horas y sábados de 12:00 a 18:00 horas. \n El servicio es gratuito, para su uso favor de presentar una identificación oficial, original y vigente."));
            services.add(new Service("INSTALACIÓN PARA PERSONAS CON DISCAPACIDAD","Seis elevadores, cuatro sobre Línea 2 y 2 sobre Línea 9, los cuales se usan si cuenta con tarjeta Libre Acceso; rampa de acceso y escaleras eléctricas sobre Línea 9."));
            services.add(new Service("VITRINA CULTURAL","Tres vitrinas localizadas entre las escaleras de la correspondencia con las Líneas 2, 8 y 9."));

            exits.add(new Exit("Oriente", "Entre calle Juan A. Mateos, Calzada Chabacano y Avenida San Antonio Abad, Colonia Vista Alegre"));
            exits.add(new Exit("Poniente", " Entre calle Manuel Caballero, calle Antonio Solís y Avenida San Antonio Abad, Colonia Obrera"));

            neighborhood.add(new MLatLng(19.409609, -99.136349));
            neighborhood.add(new MLatLng(19.408516, -99.135486));
            neighborhood.add(new MLatLng(19.409045, -99.135327));
            neighborhood.add(new MLatLng(19.409255, -99.136829));
            neighborhood.add(new MLatLng(19.408628, -99.136151));

            station = new Station("Chabacano", "metro 2", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.400778, -99.136939);

            exits.add(new Exit("Oriente", "Entre calle Coruña, Calzada Santa Anita y Calzada de Tlalpan, Colonia Viaducto Piedad"));
            exits.add(new Exit("Poniente", "Entre calle Fernando, Segovia y Calzada de Tlalpan, Colonia Alámosa"));

            neighborhood.add(new MLatLng(19.401372, -99.137669));
            neighborhood.add(new MLatLng(19.400279, -99.136806));
            neighborhood.add(new MLatLng(19.400808, -99.136647));
            neighborhood.add(new MLatLng(19.401018, -99.138149));
            neighborhood.add(new MLatLng(19.400391, -99.137471));

            station = new Station("Viaducto", "metro 2", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.395050, -99.137599);

            services.add(new Service("INSTALACIÓN PARA PERSONAS CON DISCAPACIDAD","Rampa de acceso."));
            services.add(new Service("MURAL","LOS PUEBLOS NO GUARDAN MEMORIA de Ariosto Otero Reyes."));
            services.add(new Service("METROBÚS","Línea 2 del Metrobús Xola, ubicado en avenida Xola y Calzada de Tlalpan."));

            exits.add(new Exit("Oriente", "Entre calle Juana de Arco, Napoleón y Calzada de Tlalpan, Colonia Moderna"));
            exits.add(new Exit("Poniente", "Calle Toledo, Colonia Alámos"));

            neighborhood.add(new MLatLng(19.395644, -99.138329));
            neighborhood.add(new MLatLng(19.394551, -99.137466));
            neighborhood.add(new MLatLng(19.395080, -99.137307));
            neighborhood.add(new MLatLng(19.395290, -99.138809));
            neighborhood.add(new MLatLng(19.394663, -99.138131));

            station = new Station("Xola", "metro 2", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.387485, -99.138924);

            services.add(new Service("INSTALACIÓN PARA PERSONAS CON DISCAPACIDAD","Rampa de acceso."));

            exits.add(new Exit("Oriente", "Calle Plaza Victoria y Calzada de Tlalpan, Colonia Villa de Cortés"));
            exits.add(new Exit("Poniente", "Calle Guipuzcoa, calle Ahorro Postal y Calzada de Tlalpan, Colonia Niños Héroes de Churubusco"));

            neighborhood.add(new MLatLng(19.388079, -99.138215));
            neighborhood.add(new MLatLng(19.386986, -99.137352));
            neighborhood.add(new MLatLng(19.387515, -99.137193));
            neighborhood.add(new MLatLng(19.387725, -99.138695));
            neighborhood.add(new MLatLng(19.387098, -99.138017));

            station = new Station("Villa de Cortés", "metro 2", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.379424, -99.140157);

            services.add(new Service("INSTALACIÓN PARA PERSONAS CON DISCAPACIDAD","Rampa de acceso."));

            exits.add(new Exit("Oriente", "Av. Justina, Colonia Nativitas"));
            exits.add(new Exit("Poniente", "Calle Lago Poniente, Privada de Lago y Calzada de Tlalpan, Colonia Lago"));

            neighborhood.add(new MLatLng(19.380018, -99.140887));
            neighborhood.add(new MLatLng(19.378925, -99.140024));
            neighborhood.add(new MLatLng(19.379454, -99.139865));
            neighborhood.add(new MLatLng(19.379664, -99.141367));
            neighborhood.add(new MLatLng(19.379037, -99.140689));

            station = new Station("Nativitas", "metro 2", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.369857, -99.141587);

            services.add(new Service("LÍNEA DE TROLEBÚS","Línea D (Corredor cero emisiones EJE 7 - 7A Sur) dirección oriente-poniente, sobre el eje 7 sur Municipio Libre."));
            services.add(new Service("INSTALACIÓN PARA PERSONAS CON DISCAPACIDAD","Rampa de acceso."));

            exits.add(new Exit("Oriente", "Entre calle Hamburgo, Avenida Víctor Hugo y Calzada de Tlalpan, Colonia Albert"));
            exits.add(new Exit("Poniente", "Entre calle Hamburgo, Avenida Víctor Hugo y Calzada de Tlalpan, Colonia Portales"));

            neighborhood.add(new MLatLng(19.370451, -99.142317));
            neighborhood.add(new MLatLng(19.369358, -99.141454));
            neighborhood.add(new MLatLng(19.369887, -99.141295));
            neighborhood.add(new MLatLng(19.370097, -99.142797));
            neighborhood.add(new MLatLng(19.369470, -99.142119));

            station = new Station("Portales", "metro 2", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.361792, -99.142909);

            services.add(new Service("BICIESTACIONAMIENTO","Para estacionar bicicletas, bajo tu responsabilidad."));
            services.add(new Service("LÍNEA DE TROLEBÚS","Línea D (Corredor cero emisiones EJE 7 - 7A Sur) sentido Poniente-Oriente."));
            services.add(new Service("INSTALACIÓN PARA PERSONAS CON DISCAPACIDAD","Cinco elevadores, uno sobre Línea 2, y cuatro sobre Línea 12, los cuales  se  usan si cuenta con tarjeta Libre Acceso; rampa de acceso, escaleras eléctricas, ranura guía  y placas braille para invidentes."));
            services.add(new Service("MÓDULO DE SALUD","De la Red Ángel, el horario de atención: lunes a viernes, de 07:00 a 20:00 horas y sábado de 09:00 a 14:00 horas."));

            exits.add(new Exit("Oriente", " Entre Avenida Pirineos, Avenida Repúblicas y Calzada de Tlalpan, Colonia Miravalle"));
            exits.add(new Exit("Poniente", " Entre Avenida Pirineos, Avenida Repúblicas y Calzada de Tlalpan, Colonia Portales"));

            neighborhood.add(new MLatLng(19.362386, -99.143639));
            neighborhood.add(new MLatLng(19.361293, -99.142776));
            neighborhood.add(new MLatLng(19.361822, -99.142617));
            neighborhood.add(new MLatLng(19.362032, -99.144119));
            neighborhood.add(new MLatLng(19.361405, -99.143441));

            station = new Station("Ermita", "metro 2", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.353067, -99.144749);

            services.add(new Service("INSTALACIÓN PARA PERSONAS CON DISCAPACIDAD","Rampas de acceso, ranura guía y placas braille para invidentes."));

            exits.add(new Exit("Oriente", " Entre calle de Corredores, Ciclistas y Calzada de Tlalpan, Colonia Country Club"));
            exits.add(new Exit("Poniente", " Entre calle 20 de agosto, Callejón General Anaya y Calzada de Tlalpan, Colonia Churubusco"));

            neighborhood.add(new MLatLng(19.353661, -99.145479));
            neighborhood.add(new MLatLng(19.352568, -99.144616));
            neighborhood.add(new MLatLng(19.353097, -99.144457));
            neighborhood.add(new MLatLng(19.353307, -99.145959));
            neighborhood.add(new MLatLng(19.352680, -99.145281));

            station = new Station("General Anaya", "metro 2", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

            services = new ArrayList<>();
            exits = new ArrayList<>();
            neighborhood = new ArrayList<>();

            position = new MLatLng(19.343774, -99.139455);

            services.add(new Service("BICIESTACIONAMIENTO","Para estacionar bicicletas, bajo tu responsabilidad."));
            services.add(new Service("CIBERCENTRO","Lunes a viernes de 8:00 a 20:00 horas y sábados de 12:30 a 18:00 horas. \n El servicio es gratuito, para su uso favor de presentar una identificación oficial, original y vigente."));
            services.add(new Service("LÍNEA DE TROLEBÚS","Línea A (corredor cero emisiones Eje Central) dirección norte-sur y sur-norte, sobre la calzada Taxqueña."));
            services.add(new Service("MÓDULO DE INMUJERES","En apoyo al “Programa Viaja Segura en el Transporte Público de la CDMX”, el Metro proporcionó el espacio para el Módulo ubicado a un costado del centro de monitoreo, por la salida a Av. Tasqueña. \n Horario de atención es de lunes a viernes de 8:00 a 20:00 horas."));
            services.add(new Service("MURAL","HORIZONTAL, AZUL, FUEGO, ALIENTO de Alberto Castro Leñero."));
            services.add(new Service("TREN LIGERO","Acceso a la Línea de Tren Ligero, al final de la estación al salir de los torniquetes."));
            services.add(new Service("CENTRAL CAMIONERA","Por la salida Sur de la estación, pasando el paradero de microbuses se encuentra la Central de Autobuses del Sur."));
            services.add(new Service("INSTALACIÓN PARA PERSONAS CON DISCAPACIDAD","Tres elevadores, los cuales se usan si cuenta con tarjeta Libre Acceso, rampas de acceso y ranura guía para invidentes."));
            services.add(new Service("MÓDULO DE SALUD","De la Red Ángel, el horario de atención es: lunes a viernes, de 07:00 a 20:00 horas y sábado de 09:00 a 14:00 horas."));

            exits.add(new Exit("Sur", " Entre Avenida Tasqueña, Canal de Miramontes y Calzada de Tlalpan, Colonia Campestre Churubusco"));
            exits.add(new Exit("Norte", " Entre calle Cerro de Jesús y Canal de Miramontes, Colonia Campestre Churubusco"));

            neighborhood.add(new MLatLng(19.344368, -99.140185));
            neighborhood.add(new MLatLng(19.343275, -99.139322));
            neighborhood.add(new MLatLng(19.343804, -99.139163));
            neighborhood.add(new MLatLng(19.344014, -99.140665));
            neighborhood.add(new MLatLng(19.343387, -99.139987));

            station = new Station("Tasqueña", "metro 2", position, services, exits, neighborhood);
            stations.add(station);
            marker = googleMap.addMarker(new MarkerOptions().position(station.getLatLng())
            .title(station.getName())
            .snippet(station.getLine()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(new MyImages(activity).createIconMarker(changeStyle.getStationId(station.getName(), station.getLine()), station.getLine())));
            marker.setTag(station);

    }
}
