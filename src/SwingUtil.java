package src;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;

public class SwingUtil {

    public static boolean isLocationInScreenBounds(Point location) {

        // Check if the location is in the bounds of one of the graphics devices
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] graphicsDevices = graphicsEnvironment.getScreenDevices();
        Rectangle graphicsConfigurationBounds = new Rectangle();

        // Iterate over the graphics devices
        for (int j = 0; j < graphicsDevices.length; j++) {

            // Get the bounds of the device
            GraphicsDevice graphicsDevice = graphicsDevices[j];
            graphicsConfigurationBounds.setRect(graphicsDevice.getDefaultConfiguration().getBounds());

            // Is the location in this bounds?
            graphicsConfigurationBounds.setRect(graphicsConfigurationBounds.x, graphicsConfigurationBounds.y,
                    graphicsConfigurationBounds.width, graphicsConfigurationBounds.height);
            if (graphicsConfigurationBounds.contains(location.x, location.y)) {

                return true;
            }

        }
        return false;
    }
}