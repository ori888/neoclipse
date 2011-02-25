/**
 * Licensed to Neo Technology under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Neo Technology licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.neo4j.neoclipse.view;

import org.eclipse.jface.dialogs.MessageDialog;

public class ErrorMessage
{
    private static final int MAX_DEPTH = 10;

    private static String getErrorMessage( final Throwable exception )
    {
        String message = exception.getMessage();
        Throwable throwable = exception.getCause();
        int depth = 0;
        while ( throwable != null && depth++ < MAX_DEPTH )
        {
            if ( throwable.getMessage() != null )
            {
                message += ": " + throwable.getMessage();
            }
            throwable = throwable.getCause();
        }
        return message;
    }

    public static void showDialog( final String heading, final String message )
    {
        UiHelper.asyncExec( new Runnable()
        {
            public void run()
            {
                MessageDialog.openInformation( null, heading, message );
            }
        } );
    }

    public static void showDialog( final String heading,
            final Throwable throwable )
    {
        throwable.printStackTrace();
        String message = getErrorMessage( throwable );
        showDialog( heading, message );
    }
}
