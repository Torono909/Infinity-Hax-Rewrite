public class FlyAbility {
    
    // The fly speed
    private static final float FLY_SPEED = 0.05f;
    
    // The maximum altitude the player can fly at
    private static final int MAX_ALTITUDE = 128;
    
    // Whether or not the player is currently flying
    private boolean isFlying = false;
    
    // Activate the fly ability
    public void activate(EntityPlayer player) {
        if (!player.world.isRemote) {
            isFlying = true;
            player.capabilities.allowFlying = true;
            player.capabilities.isFlying = true;
            player.sendPlayerAbilities();
        }
    }
    
    // Deactivate the fly ability
    public void deactivate(EntityPlayer player) {
        if (!player.world.isRemote) {
            isFlying = false;
            player.capabilities.allowFlying = false;
            player.capabilities.isFlying = false;
            player.sendPlayerAbilities();
        }
    }
    
    // Update the player's position and state when flying
    public void onUpdate(EntityPlayer player) {
        if (!isFlying || player.isSpectator() || player.isCreative()) {
            return;
        }
        
        // Calculate the new position
        double x = player.posX + player.getLookVec().x * FLY_SPEED;
        double y = player.posY + player.getLookVec().y * FLY_SPEED;
        double z = player.posZ + player.getLookVec().z * FLY_SPEED;
        
        // Check if the new position is within bounds
        if (y > MAX_ALTITUDE) {
            y = MAX_ALTITUDE;
        } else if (y < 0) {
            y = 0;
        }
        
        // Set the new position
        player.setPositionAndUpdate(x, y, z);
    }
    
}
