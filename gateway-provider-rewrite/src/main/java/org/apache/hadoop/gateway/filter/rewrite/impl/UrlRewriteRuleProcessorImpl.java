/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.gateway.filter.rewrite.impl;

import org.apache.hadoop.gateway.filter.rewrite.api.UrlRewriteRuleDescriptor;
import org.apache.hadoop.gateway.filter.rewrite.ext.UrlRewriteMatchDescriptor;
import org.apache.hadoop.gateway.filter.rewrite.ext.UrlRewriteMatchDescriptorExt;
import org.apache.hadoop.gateway.filter.rewrite.ext.UrlRewriteMatchProcessorExt;
import org.apache.hadoop.gateway.filter.rewrite.spi.UrlRewriteContext;
import org.apache.hadoop.gateway.filter.rewrite.spi.UrlRewriteStepProcessor;
import org.apache.hadoop.gateway.filter.rewrite.spi.UrlRewriteStepStatus;

public class UrlRewriteRuleProcessorImpl implements UrlRewriteStepProcessor<UrlRewriteRuleDescriptor> {

  private UrlRewriteMatchProcessorExt matchProcessor;

  @Override
  public String getType() {
    return "rule";
  }

  @Override
  public void initialize( UrlRewriteRuleDescriptor descriptor ) throws Exception {
    UrlRewriteMatchDescriptor matchDescriptor = new UrlRewriteMatchDescriptorExt();
    matchDescriptor.operation( "matches" );
    matchDescriptor.flow( descriptor.flow() );
    matchDescriptor.template( descriptor.template() );
    matchProcessor = new UrlRewriteMatchProcessorExt();
    matchProcessor.initialize( matchDescriptor );
  }

  @Override
  public UrlRewriteStepStatus process( UrlRewriteContext context ) throws Exception {
    return matchProcessor.process( context );
  }

  @Override
  public void destroy() {
    matchProcessor.destroy();
  }

}
