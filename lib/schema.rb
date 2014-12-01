require 'field'

class Schema
  attr_reader :name, :fields

  def self.unmarshal(json_schema)
    params = json_schema.inject({}) do |params, (key, value)|
      params[key.to_sym] = value
      params
    end
    new(params)
  end

  def initialize(params)
    @name = params[:name]
    @fields = params[:fields]
  end
end
