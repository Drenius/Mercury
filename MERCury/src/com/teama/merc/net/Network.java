package com.teama.merc.net;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 * @from merc in com.teama.merc.net
 * @by opiop65
 * @website www.wessles.com
 * @license (C) Jan 17, 2014 www.wessles.com This file, and all others of the project 'MERCury' are licensed under WTFPL license. You can find the license itself at http://www.wtfpl.net/about/.
 */

public class Network extends Listener
{
    
    // called when a remote client is connected. Client must be connected before any transferring of packets can take place. This method will not block any threads by design.
    @Override
    public void connected(Connection connection)
    {
    }
    
    // Called when a remote client is disconnected.
    @Override
    public void disconnected(Connection connection)
    {
    }
    
    // Called when an object is received from a remote client. This method will not block any threads by design.
    @Override
    public void received(Connection connection, Object object)
    {
    }
    
    // Called when the connection is below the idle threshold.
    @Override
    public void idle(Connection connection)
    {
    }
}