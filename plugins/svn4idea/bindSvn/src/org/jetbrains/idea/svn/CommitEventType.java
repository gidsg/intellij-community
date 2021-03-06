/*
 * Copyright 2000-2013 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jetbrains.idea.svn;

/**
 * Created with IntelliJ IDEA.
 * User: Irina.Chernushina
 * Date: 2/25/13
 * Time: 6:51 PM
 */
public enum CommitEventType {
  adding("Adding"),
  deleting("Deleting"),
  sending("Sending"),
  replacing("Replacing"),
  transmittingDeltas("Transmitting file data");

  private final String myText;

  private CommitEventType(String text) {
    myText = text;
  }

  private String getText() {
    return myText;
  }

  public static CommitEventType create(String text) {
    text = text.trim();
    final CommitEventType[] values = CommitEventType.values();
    for (CommitEventType value : values) {
      if (value.getText().equals(text)) return value;
    }
    return null;
  }
}
