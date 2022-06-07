#!/bin/bash

( rabbitmqctl wait --timeout 60 $RABBITMQ_PID_FILE ; \
rabbitmqctl add_user $RABBITMQ_USER $RABBITMQ_PASSWORD 2>/dev/null ; \
rabbitmqctl set_user_tags $RABBITMQ_USER administrator ; \
rabbitmqctl add_vhost spring_host; \
rabbitmqctl set_permissions -p spring_host $RABBITMQ_USER ".*" ".*" ".*"; \
echo "*** User '$RABBITMQ_USER' with password '$RABBITMQ_PASSWORD' completed. ***" ; \
echo "*** Log in the WebUI at port 15672 (example: http:/localhost:15672) ***" ; \
rabbitmqadmin declare exchange --vhost=spring_host name=spring_exchange type=direct -u $RABBITMQ_USER -p $RABBITMQ_PASSWORD ; \
rabbitmqadmin declare queue --vhost=spring_host name=spring_queue durable=true -u $RABBITMQ_USER -p $RABBITMQ_PASSWORD ; \
rabbitmqadmin  declare binding --vhost=spring_host source=spring_exchange destination_type=queue destination=spring_queue routing_key=spring_routing_key -u $RABBITMQ_USER -p $RABBITMQ_PASSWORD ; \
echo "*** Exchanges, Queues and Bindings created ***" ) &

 rabbitmq-server $@