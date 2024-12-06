/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hertzbeat.common.serialize;

import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.hertzbeat.common.cache.CacheFactory;
import org.apache.hertzbeat.common.cache.CommonCacheService;
import org.apache.hertzbeat.common.entity.dto.vo.NoticeReceiverVO;

import java.io.IOException;

/**
 * 2024-12-06
 * Phone Desensitizing serializer
 */
public class PhoneDesensitizationSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String phone, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String phoneDesensitization = "";
        CommonCacheService<String, Object> desensitizationMapCache = CacheFactory.getDesensitizationMapCache();
        if (StrUtil.isNotBlank(phone)){
            phoneDesensitization = DesensitizedUtil.mobilePhone(phone);
            NoticeReceiverVO currentValue = (NoticeReceiverVO)jsonGenerator.getOutputContext().getCurrentValue();
            desensitizationMapCache.put(currentValue.getId()+"_"+phoneDesensitization, phone);
        }

        jsonGenerator.writeString(phoneDesensitization);
    }
}