/*
 * Copyright 2000-2012 JetBrains s.r.o.
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
package com.intellij.debugger.ui;

import com.intellij.debugger.ui.breakpoints.BreakpointWithHighlighter;
import com.intellij.debugger.ui.breakpoints.ExceptionBreakpoint;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.xdebugger.breakpoints.ui.XBreakpointGroupingRule;
import com.intellij.xdebugger.breakpoints.ui.XBreakpointsGroupingPriorities;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class XBreakpointGroupingByPackageRule<B> extends XBreakpointGroupingRule<B, XBreakpointPackageGroup> {

  protected XBreakpointGroupingByPackageRule() {
    super("XBreakpointGroupingByPackageRule", "Group by package");
  }

  @Override
  public int getPriority() {
    return XBreakpointsGroupingPriorities.BY_PACKAGE;
  }

  @Override
  public XBreakpointPackageGroup getGroup(@NotNull B breakpoint, @NotNull Collection<XBreakpointPackageGroup> groups) {
    String packageName = null;
    if (breakpoint instanceof BreakpointWithHighlighter) {
      packageName = ((BreakpointWithHighlighter)breakpoint).getPackageName();
    }
    else if (breakpoint instanceof ExceptionBreakpoint) {
      packageName = ((ExceptionBreakpoint)breakpoint).getPackageName();
    }
    if (packageName == null) {
      return null;
    }
    for (XBreakpointPackageGroup group : groups) {
      if (StringUtil.equals(group.getPackageName(), packageName)) {
        return group;
      }
    }
    return new XBreakpointPackageGroup(packageName);
  }
}
