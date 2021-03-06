/*
  Copyright (c) 2018-present, SurfStudio LLC, Fedor Atyakshin.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */
package ru.surfstudio.android.analytics;

import androidx.annotation.NonNull;

import java.util.Map;

/**
 * Обобщенный api для работы с аналитикой
 *
 * Реализующий класс должен отправить событие при необходимости преобразовав параметры в нужный вид
 */
public interface Analytics {

    /**
     * Событие без параметров
     *
     * @param event название события
     */
    void sendEvent(String event);

    /**
     * Событие с параметрами
     *
     * @param event  название события
     * @param params параметры
     */
    void sendEvent(String event, @NonNull Map<String, String> params);

    /**
     * Установить параметр пользователя
     *
     * @param key название параметра
     * @param value значение
     */
    void setUserProperty(String key, String value);
}
