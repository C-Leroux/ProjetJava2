package mediatheque;

import java.util.EventListener;

public interface DelaiRestitutionDepasseListener extends EventListener {

    public void exemplaireNonRestitue(DelaiDepasseEvent evt);
    public String toJson();
}
