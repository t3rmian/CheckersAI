/* 
 * Copyright 2015 Damian Terlecki.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package checkers;

import checkers.ui.GUI;
import checkers.ui.TUI;

public class UILoader {

    /**
     * @param args the command line arguments, -ui=tui enables text user
     * interface, grading values will be loaded from Checkers.properties (or
     * default ones if the file does not exist)
     */
    public static void main(String[] args) {
        for (String arg : args) {
            if (arg.toLowerCase().equals("-ui=tui")) {
                TUI.main(args);
                return;
            }
        }
        if (args.length != 0) {
            System.out.println("Wrong arguments\nUse -ui=tui to enable text user interface, grading values will be loaded from Checkers.properties (or default ones if the file does not exist)");
        }
        GUI.main(args);
    }

}
