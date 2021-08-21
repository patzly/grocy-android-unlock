/*
 * This file is part of Grocy Android.
 *
 * Grocy Android is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Grocy Android is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Grocy Android. If not, see http://www.gnu.org/licenses/.
 *
 * Copyright (c) 2020-2021 by Patrick Zedler and Dominic Zedler
 */

package xyz.zedler.patrick.grocy.unlock;

import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  private final static int PURCHASED = 1;
  private final static int NOT_PURCHASED = 0;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    try {
      String packageName = getApplicationContext().getPackageName();
      String source;
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        source = getPackageManager().getInstallSourceInfo(packageName).getInitiatingPackageName();
      } else {
        source = getPackageManager().getInstallerPackageName(packageName);
      }
      setResult(source != null && source.equals("com.android.vending") ? PURCHASED : NOT_PURCHASED);
    } catch (Exception ignored) {
    }

    finish();
  }
}
